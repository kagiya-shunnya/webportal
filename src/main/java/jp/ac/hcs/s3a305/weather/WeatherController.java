package jp.ac.hcs.s3a305.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {
	

    @Autowired
    private WeatherService weatherService;
	/*
	 * 都市コードをもとに天気予報の情報を取得する
	 * @param zipcode 検索する都市コード
	 * @oaram principal ログイン情報
	 * @param model
	 * @return 結果画面 - 天気予報
	 */
	@GetMapping("/weather")
	public String postWeather(Principal principal, Model model) {
		
		String citycode = "016010";
		
		WeatherEntity weatherEntity = weatherService.getWeather(citycode);
		model.addAttribute("weatherEntity",weatherEntity);
		
		return "weather/weather";
	}
	
	

}
