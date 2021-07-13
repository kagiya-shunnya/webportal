
package jp.ac.hcs.s3a305.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

	 @Autowired
	 private GourmetService gourmetService;	 
		/*
		 * 郵便番号から住所を検索し、結果画面を表示する
		 * @param zipcode 検索する郵便番号（ハイフンなし）
		 * @oaram principal ログイン情報
		 * @param model
		 * @return 結果画面 - 郵便番号
		 */
	 @PostMapping("/gourmet")
	 public String getShops(@RequestParam("shopname") String shopname,
			Principal principal, Model model) {
		 /*北海道に固定*/
		 String large_service_area = "SS40";
			
		ShopEntity shopEntity = gourmetService.getShop(shopname,large_service_area);
		model.addAttribute("shopEntity",shopEntity);
			
	return "gourmet/gourmet";
	}		

}
