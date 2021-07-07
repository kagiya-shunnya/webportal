package jp.ac.hcs.s3a305.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/*APIのリクエスト先URL*/
	 private static final String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";
	 
	 /*
	  * 気象情報取得ロジック
	  * @param cityCode 都市コード
	  * @return ZipCodeEntitiy
	  */
	 
	 public WeatherEntity getWeather(String cityCode) {
		 
		 
		 //APiへアクセスして、結果を取得
		 String json = restTemplate.getForObject(URL, String.class, cityCode);
		 
		 //エンティティクラスを作成
		 WeatherEntity weatherEntity = new WeatherEntity();
		 
		 //jsonクラスへの変換失敗のため、例外処理
		 try {
			 
			 //変換クラスを作成し、文字列からjsonクラスへ変換する（例外の可能性あり）
			 ObjectMapper mapper = new ObjectMapper();
			 JsonNode node = mapper.readTree(json);
			 
			 
			 //追加課題
			 String description = node.get("description").get("text").asText();
			 weatherEntity.setDescription(description);
			 
			 //forecast(配列)をForEachで配列分繰り返す
			 for(JsonNode forecast : node.get("forecasts")) {
				 WeatherData data = new WeatherData();
				 //datalabelをDataクラスへ設定
				 data.setDateLabel(forecast.get("dateLabel").asText());
				 //telopをDataクラスへ設定
				 data.setTelop(forecast.get("telop").asText());
				 //DataクラスをEntityの配列に追加
				 weatherEntity.getForecasts().add(data);

			 } 
			 
		 }catch(IOException e) {
			 //例外発生時は、エラーメッセージの詳細を標準エラー出力
			 e.printStackTrace();
		 }
		 
		 return weatherEntity;
	 }

}
