package com.gem.erhuo.service;

import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.dao.GoodsDao;
import com.gem.erhuo.dao.GoodsImagesDao;
import com.gem.erhuo.entity.Goods;

public class GoodsService {

	GoodsDao gd = new GoodsDao();
	GoodsImagesDao gid = new GoodsImagesDao();
	//根据商品id获得商品
	public Goods getGoodsById(int goodId){
		return gd.getByID(new Goods(), goodId);
	}
	public void update(Goods goods){
		gd.update(goods);
	}
	
	//根据商品id获得商品
	public List<Goods> getGoodsListById(List<Integer> listId){
		List<Goods> listGoods = new ArrayList<Goods>();
		for(Integer i : listId){
			Goods g = gd.getByID(new Goods(), i);
			listGoods.add(g);
		}
		return listGoods;
	}
	
	// 获得商品集合
	public List<Goods> getAllGoods(Goods goods) {
		return gd.getAll(goods);
	}

	// 获得商品图片集合
	public List<String> getGoodsUrls(int goodsId) {
		return gid.getGoodsImagesUrl(goodsId);
	}

	// 获得分页商品
	public List<Goods> getPagedGoods(int curPage, int pageSize) {
		return gd.getPagedGoods(curPage, pageSize);
	}

	// 分类查询获得商品对象
	public List<Goods> getClassifiGoods(int tag, int curPage, int pageSize) {
		return gid.getClassificaImages(tag, curPage, pageSize);
	}

	// 获得分页在售中的商品
	public List<Goods> getSellingPagedGoods(int curPage, int pageSize, int userId) {
		return gd.getSellingPagedGoods(curPage, pageSize, userId);
	}

	// 获得分页在售中的商品
	public List<Goods> getSoldPagedGoods(int curPage, int pageSize, int userId) {
		return gd.getSoldPagedGoods(curPage, pageSize, userId);
	}
	public List<Goods> getGoodsByMarketId(int curPage, int pageSize, int marketId) {
		return gd.getGoodsByMarketId(curPage,pageSize,marketId);
	}

}
