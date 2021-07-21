package jp.ac.hcs.s3a305.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import jp.ac.hcs.s3a305.WebConfig;

/**
 * SQLで取得した結果をCSVファイルとしてサーバに保存する.
 */
public class ReportRowCallbackHandler implements RowCallbackHandler {

	@Override
	public void processRow(ResultSet rs) throws SQLException {

		try {

			File directory = new File(WebConfig.OUTPUT_PATH);
			if (!directory.exists()) {
				directory.mkdir();
			}

			File file = new File(WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);
			do {
				// Reportテーブルのデータ構造
				String str = rs.getString("user_name") + ","
						+ rs.getString("class_number") + ","
						+ rs.getString("student_number") + ","
						+ rs.getString("division") + ","
						+ rs.getDate("day") + ","
						+ rs.getString("company_name");

				bw.write(str);
				bw.newLine();
			} while (rs.next());
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}

	}

}
