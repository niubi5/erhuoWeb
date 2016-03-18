package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GoodsDao;
import com.gem.erhuo.dao.GoodsImagesDao;
import com.gem.erhuo.entity.Goods;

public class GoodsService {
	
	GoodsDao gd = new GoodsDao();
	GoodsImagesDao gid = new GoodsImagesDao();
	// 获得商品集合
	public List<Goods> getAllGoods(Goods goods){
		return gd.getAll(goods);
	}
	// 获得商品图片集合
	public List<String> getGoodsUrls(int goodsId){
		return gid.getGoodsImagesUrl(goodsId);
	}

}