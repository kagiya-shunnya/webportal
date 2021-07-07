
package jp.ac.hcs.s3a305.gourmet;

//@Controller
//public class ShopController {
//
//	 @Autowired
//	 private GourmetService gourmetService;
//	 
//	 /*北海道に固定*/
//	 String large_service_area = "SS40";
//	 
//	 /*apikeyの設定*/
//	 String apikey = "0836b12cc78c1b49";
//	 
//		/*
//		 * 郵便番号から住所を検索し、結果画面を表示する
//		 * @param zipcode 検索する郵便番号（ハイフンなし）
//		 * @oaram principal ログイン情報
//		 * @param model
//		 * @return 結果画面 - 郵便番号
//		 */
//	 @PostMapping("/gourmet")
//	 public String getZipCode(@RequestParam("shopname") String shopname,
//			Principal principal, Model model) {
//			
//		ShopEntity shopEntity = GourmetService.getZip(shopname);
//		model.addAttribute("shopEntity",shopEntity);
//			
//	return "gourmet/gourmet";
//	}		
//
//}
