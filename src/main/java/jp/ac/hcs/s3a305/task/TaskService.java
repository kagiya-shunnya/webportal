package jp.ac.hcs.s3a305.task;


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
 * タスク情報を操作する
 */
@Transactional
@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	/*
	 * 指定したユーザIDのタスク情報を全件取得する
	 * @param userId ユーザID
	 * @return TaskEntity
	 */
	
	public TaskEntity selectAll(String userId) {
		TaskEntity taskEntity;
		try {
			taskEntity = taskRepository.selectAll(userId);
		}catch (DataAccessException e) {
			e.printStackTrace();
			taskEntity = null;
		}
		
		return taskEntity;
	}
	/*
	 * 入力されたタスク内容と期限日でDBに登録する
	 * @param user_id ユーザID
	 * @param comment タスク内容
	 * @param limitday 期限日
	 * @return １件以上あればtrue,それ以外はfalse
	 */
	
	public boolean insert(String user_id, String comment, String limitday) {
		TaskData taskData = refillToData(user_id,comment,limitday);
		
		int count = taskRepository.insertOne(taskData);
		return count > 0;
		
	}
	
	private TaskData refillToData(String user_id, String comment, String limitDay) {
		TaskData taskData = new TaskData();
		taskData.setUser_id(user_id);
		taskData.setComment(comment);
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			taskData.setLimitday(format.parse(limitDay));
			
		}catch (ParseException e) {
			//何もしない
			
		}
		return taskData;
	}
	
	/*
	 * 指定されたIDのタスクを削除する
	 * @param id タスクID
	 * @return １件以上であればtrue、それ以外はfalse
	 */
	public boolean delete(int id) {
		int count;
		try {
			count = taskRepository.deleteOne(id);
		}catch (DataAccessException e) {
			e.printStackTrace();
			count = 0;
		}
		return count > 0;
	}
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
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
