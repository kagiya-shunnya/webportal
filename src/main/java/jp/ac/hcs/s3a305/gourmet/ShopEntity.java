package jp.ac.hcs.s3a305.gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShopEntity {
	
	private String shopname;
	
	private List<ShopData> results = new ArrayList<ShopData>();
	

}
