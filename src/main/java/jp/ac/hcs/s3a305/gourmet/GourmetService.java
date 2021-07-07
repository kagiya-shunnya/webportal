//package jp.ac.hcs.s3a305.gourmet;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Transactional
//@Service
//public class GourmetService {
//	
//	public  ShopEntitiy getShops(String shopname, String large_service_area) {
//		
//		String json = restTemplate.getForObject(URL, String.class, API_KEY, shopname, large_service_area);
//		
//		ShopEntity shopEntity = new ShopEntity();
//		shopEntity.setSearchWord(shopname);
//		
//		try {
//			ObjectMapper mapper = new ObjrctMapper();
//			JsonNode node = mapper.readTree(json);
//			
//			
//			for (JsonNode shop : node.get("results").get("shop")){
//				ShopData shopData = new ShopData();
//				shopData.setId(shop.get("id").asText());
//				shopData.setName(shop.get("name").asText());
//				shopData.setlogo_image(shop.get)
//				
//				
//				shopEntity.getShops().add(shopData);
//			}
//		
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		return shopEntity
//	}
//}

