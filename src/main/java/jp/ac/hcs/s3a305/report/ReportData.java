package jp.ac.hcs.s3a305.report;

import java.util.Date;

import lombok.Data;

@Data
public class ReportData {
	
	//氏名
	private String user_name;
	//クラス
	private String class_number;
	//番号
	private String student_number;
	//状態
	private String division;
	//日付
	private Date day;
	//会社名
	private String company_name;
	//試験会場
//	private String test_center;
//	//試験場所
//	private String test_area;
//	//試験内容
//	private String test_contents;
//	//受検内容：その他の場合
//	private String contents;
//	//面接内容
//	private String interview_contents;
//	//面接概要
//	private String theme;
//	//出題内容
//	private String question_contents;
	
	

}
