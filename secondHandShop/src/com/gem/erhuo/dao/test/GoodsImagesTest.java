package com.gem.erhuo.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.erhuo.dao.GoodsImagesDao;

public class GoodsImagesTest {
	GoodsImagesDao gid = new GoodsImagesDao();
	@Test
	public void getGoodsImagesUrlTest(){
		List<String> urls = gid.getGoodsImagesUrl(5);
		System.out.println(urls);
	}

}
