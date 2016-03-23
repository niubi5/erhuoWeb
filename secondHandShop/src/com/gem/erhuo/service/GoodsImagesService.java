package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GoodsImagesDao;

public class GoodsImagesService {
	GoodsImagesDao gid = new GoodsImagesDao();
	// 获得商品图片集合
	public List<String> getGoodsImages(int goodsId){
		return gid.getGoodsImagesUrl(goodsId);
	}
}
