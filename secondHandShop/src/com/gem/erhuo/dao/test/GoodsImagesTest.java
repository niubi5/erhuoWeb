package com.gem.erhuo.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.erhuo.dao.GoodsImagesDao;
import com.gem.erhuo.entity.Goods;

public class GoodsImagesTest {
	GoodsImagesDao gid = new GoodsImagesDao();
	@Test
	public void getGoodsImagesUrlTest(){
		List<String> urls = gid.getGoodsImagesUrl(5);
		System.out.println(urls);
	}
	
	@Test
	public List<Goods> getClassificaImages(){
		List<Goods> listGoods=gid.getClassificaImages(1);
		System.out.println(listGoods);
		
		return listGoods;
		
	}
}
