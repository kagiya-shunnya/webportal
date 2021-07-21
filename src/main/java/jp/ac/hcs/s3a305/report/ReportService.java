package jp.ac.hcs.s3a305.report;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 受験報告情報を操作する。
 */
@Transactional
@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;
	
	public ReportEntity selectAll(String day) {
		ReportEntity reportEntity;
		try {
			reportEntity = reportRepository.selectAll();
		}catch (DataAccessException e) {
			e.printStackTrace();
			reportEntity = null;
		}
		
		return reportEntity;
	}
	
	/*
	 * 入力された受験報告内容をデータベースに登録する
	 * @param user_id ユーザID
	 * @param comment タスク内容
	 * @param limitday 期限日
	 * @return １件以上あればtrue,それ以外はfalse
	 */
	
	public boolean insert(String user_name, String class_number, String student_number
			,String division,String day,String company_name ) {
		ReportData reportData = refillToData(user_name,class_number,student_number,division,day,company_name);
		
		int count = reportRepository.insertOne(reportData);
		return count > 0;
		
	}
	
	/*
	 * 入力項目をReportDataへ変換する
	 * （このメソッドは入力チェックを実施したうえで呼び出すこと）
	 * @return ReportData
	 */
	private ReportData refillToData(String user_name, String class_number, String student_number
			,String division,String day,String company_name) {
		ReportData reportData = new ReportData();
		reportData.setUser_name(user_name);
		reportData.setClass_number(class_number);
		reportData.setStudent_number(student_number);
		reportData.setDivision(division);
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			reportData.setDay(format.parse(day));
			
		}catch (ParseException e) {
			//何もしない
			
		}
		reportData.setCompany_name(company_name);
		
		

		return reportData;
	}
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void reportListCsvOut() throws DataAccessException {
		reportRepository.reportListCsvOut();
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
	


}
	
	


