package jp.ac.hcs.s3a305.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/*
 * 天気予報の検索結果の天気情報
 * 各項目のデータ仕様については、APIの仕様を参照する
 * https://weather.tsukumijims.net/
 */
@Data
public class WeatherEntity {
	
	/*タイトル*/
	private String title;
	
	/*説明文*/
	private String description;
	
	/*天気予報のリスト*/
	private List<WeatherData> forecasts = new ArrayList<WeatherData>();

}
