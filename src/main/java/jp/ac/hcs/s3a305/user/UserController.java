package jp.ac.hcs.s3a305.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	@Autowired
	UserService userService;
	/*
	 * 
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

}
