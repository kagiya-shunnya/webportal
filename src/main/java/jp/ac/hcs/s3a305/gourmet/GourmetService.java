package jp.ac.hcs.s3a305.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	 /*apikeyの設定*/
	 private static final String API_KEY = "0836b12cc78c1b49";
	 /*API リクエスト　URL*/
	 private static final String URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json";
	
	public ShopEntity getShop(String shopname, String large_service_area) {
		
		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
		
		ShopEntity shopEntity = new ShopEntity();
		//shopEntity.setSearchWord(shopname);
		shopEntity.setShopname("「" + shopname + "」の検索結果");
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			
			
			for (JsonNode shop : node.get("results").get("shop")){
				ShopData shopData = new ShopData();
				shopData.setId(shop.get("id").asText());
				shopData.setName(shop.get("name").asText());
				shopData.setLogo_image(shop.get("logo_image").asText());
				shopData.setName_kana(shop.get("name_kana").asText());
				shopData.setAddress(shop.get("address").asText());
				shopData.setAccess(shop.get("access").asText());
				shopData.setUrl(shop.get("urls").get("pc").asText());
				//shopData.setImage(shop.get("photo").get("mobile").get("|").asText());
				JsonNode images = shop.get("photo");
				images = images.get("mobile");
				shopData.setImage(images.get("l").asText());

				
				
				shopEntity.getResults().add(shopData);
			}
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		return shopEntity;
	}
}

