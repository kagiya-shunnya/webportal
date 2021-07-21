package jp.ac.hcs.s3a305.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 受験報告のデータを管理する.
 * - Userテーブル
 */
@Repository
public class ReportRepository {

	/** SQL 全件取得（ユーザID昇順） */
	private static final String SQL_SELECT_ALL = "SELECT * FROM report order by day desc";
	
	private static final String SQL_SELECT_ALL_X = "SELECT * FROM report";

	/** SQL 1件追加 */
	private static final String SQL_INSERT_ONE = "INSERT INTO report(user_name,class_number,student_number,division,day,company_name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

//	/** SQL 1件削除 */
//	private static final String SQL_DELETE_ONE = "DELETE FROM m_user WHERE user_id = ?";

	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * Userテーブルから全データを取得.
	 * @return UserEntity
	 * @throws DataAccessException
	 */
	public ReportEntity selectAll() throws DataAccessException {
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL);
		ReportEntity reportEntity = mappingSelectResult(resultList);
		return reportEntity;
	}

	/**
	 * Reportテーブルから取得したデータをReportEntity形式にマッピングする.
	 * @param resultList Reportテーブルから取得したデータ
	 * @return ReportEntity
	 */
	private ReportEntity mappingSelectResult(List<Map<String, Object>> resultList) {
		ReportEntity entity = new ReportEntity();

		for (Map<String, Object> map : resultList) {
			ReportData data = new ReportData();
			data.setUser_name((String) map.get("user_name"));
			data.setClass_number((String)map.get("class_number"));
			data.setStudent_number((String)map.get("student_number"));
			data.setDivision((String)map.get("division"));
			data.setDay((Date)map.get("day"));
			data.setCompany_name((String)map.get("company_name"));
//			data.setTest_center((String)map.get("test_center"));
//			data.setTest_area((String)map.get("test_area"));
//			data.setTest_contents((String)map.get("test_contents"));
//			data.setContents((String)map.get("contents"));
//			data.setInterview_contents((String)map.get("interview_contents"));
//			data.setTheme((String)map.get("theme"));
//			data.setQuestion_contents((String)map.get("question_contents"));

			entity.getReportlist().add(data);
		}
		return entity;
	}
//
	/**
	 * Reportテーブルにデータを1件追加する.
	 * @param data 追加する受験報告情報
	 * @return 追加データ数
	 */
	public int insertOne(ReportData data) throws DataAccessException {
		int rowNumber = jdbc.update(SQL_INSERT_ONE,
				data.getUser_name(),
				data.getClass_number(),
				data.getStudent_number(),
				data.getDivision(),
				data.getDay(),
				data.getCompany_name());
		return rowNumber;
	}
	
	public void reportListCsvOut() throws DataAccessException {

		// CSVファイル出力用設定
		ReportRowCallbackHandler handler = new ReportRowCallbackHandler();

		jdbc.query(SQL_SELECT_ALL_X, handler);
	}
}


