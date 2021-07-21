package jp.ac.hcs.s3a305.report;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/*
 * ユーザ情報をまとめて管理するためのエンティティクラス
 */
@Data
public class ReportEntity {
	 
	/*ユーザ情報のリスト*/
	private List<ReportData> reportlist = new ArrayList<ReportData>();

}
