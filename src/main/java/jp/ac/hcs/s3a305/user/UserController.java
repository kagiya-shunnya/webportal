package jp.ac.hcs.s3a305.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	@Autowired
	UserService userService;
	/*
	 * ユーザの一覧画面を表示する。
	 */
	@GetMapping("/user/list")
	public String postUser(Principal principal, Model model) {
		log.info("[" + principal.getName() + "]タスク検索：");
		UserEntity userEntity = userService.selectAll(principal.getName());
		if(userEntity == null) {
			log.info("[" + principal.getName() + "]タスク検索：エラーが発生しました。");
		}
		model.addAttribute("userEntity",userEntity);
		return "user/userList";
	}
	/*
	 * ユーザ情報登録画面（管理者用）を表示する
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return
	 */
	

	@GetMapping("/user/insert")
	public String getUserInsert(UserForm form, Model model) {
		return "user/insert";
	}
	
	/*
	 * 1件分のユーザ情報をデータベースに登録する
	 * @param form 登録するユーザ情報（パスワードは平文）
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @param ユーザ一覧画面
	 */
	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form, model);
		}
		UserData userdata = userService.refillToData(form);
		boolean isSuccess = userService.insertOne(userdata);
		if(isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		
		return postUser(principal, model);
		
	}
}


