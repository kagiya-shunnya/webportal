package jp.ac.hcs.s3a305.report;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.hcs.s3a305.WebConfig;
import lombok.extern.slf4j.Slf4j;

/*
 * 受験報告に関する機能・画面を制御する。
 */
@Slf4j
@Controller
public class ReportController {
	
	@Autowired
	ReportService reportService;
	/*
	 * 受験報告一覧画面を表示する。
	 * @param model
	 * @return 受験報告画面
	 */
	@GetMapping("/report")
	public String getReportList(Principal principal, Model model) {
		log.info("[" + principal.getName() + "]タスク検索：");
		ReportEntity reportEntity = reportService.selectAll(principal.getName());
		if(reportEntity == null) {
			log.info("[" + principal.getName() + "]タスク検索：エラーが発生しました。");
		}
		model.addAttribute("reportEntity",reportEntity);
		return "/report";
	}
	
	/*
	 * 受験報告を追加する。
	 * @return 受験報告画面
	 */
     @PostMapping("/reportinsert")
      public String insertTask(@RequestParam("user_name")String user_name,
		                       @RequestParam("class_number")String class_number,
		                       @RequestParam("student_number")String student_number,
		                       @RequestParam("division")String division,
		                       @RequestParam("day")String day,
		                       @RequestParam("company_name")String company_name,
		                       Principal principal, Model model) {
      boolean isSuccess = reportService.insert(user_name ,class_number,student_number,
    		  division,day,company_name);
      if(isSuccess) {
	    log.info("成功");
      }else {
	    log.info("失敗");
      }
	   return getReportList(principal, model);
   }
 	@GetMapping("/detail")
 	public String getLogin(Model model) {
 		return "/reportdetail";
 	}
 	
	/**
	 * 自分の全てのタスク情報をCSVファイルとしてダウンロードさせる.
	 * @param principal ログイン情報
	 * @param model
	 * @return タスク情報のCSVファイル
	 */
	@PostMapping("/report/csv")
	public ResponseEntity<byte[]> getReportCsv(Principal principal, Model model) {

		final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV;

		log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

		// CSVファイルをサーバ上に作成
		reportService.reportListCsvOut();

		// CSVファイルをサーバから読み込み
		byte[] bytes = null;
		try {
			bytes = reportService.getFile(OUTPUT_FULLPATH);
			log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
		} catch (IOException e) {
			log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
			e.printStackTrace();
		}

		// CSVファイルのダウンロード用ヘッダー情報設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", WebConfig.FILENAME_TASK_CSV);

		// CSVファイルを端末へ送信
		return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
	}
}	
