package com.gem.erhuo.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.service.GoodService;

public class GoodServletTest {
	GoodService gs = new GoodService();

//	@Test
//	public void saveTest() {
//		Goods good = new Goods();
//		good.setUserId(1);
//		good.setName("人工体学座椅");
//		good.setImformation("采用网布（成分：杜邦纱+聚酯纤为）、环保布+高品质记忆棉等弹性环保材料制作坐垫和椅背，在工艺设计上是非常适应未来家具新潮流的发展。");
//		good.setTypeId(2);
//		good.setSoldPrice(489);
//		good.setBuyPrice(690);
//		good.setMarketId(4);
//		good.setLongitude(38.65777);
//		good.setLatitude(104.08296);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		try {
//			good.setPubTime(sdf.parse("2015-12-23"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		good.setState(2);
//		gs.save(good);
//	}
	
	@Test
	public void getPagegetPage(){
		List<Goods> list = gs.getPage(new Goods(), 2, 1);
		System.out.println(list.toString());
	}
}
