package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GoodsDao;
import com.gem.erhuo.entity.Goods;

public class GoodService {
	private GoodsDao gd = new GoodsDao();
	//新增商品
	public int save(Goods goods){
		return gd.save(goods);
	}
	//获得分页商品
	public List<Goods> getPage(Goods goods, int curPage, int pageSize){	
		return gd.getPage(goods, curPage, pageSize);
	}
	//获得搜索的集合
	public List<Goods> getGoodsList(String word,int curPage, int pageSize){
		return gd.getGoodsList(word,curPage,pageSize);
	}
	
	public Goods getById(int goodsId){
		return gd.getByID(new Goods(), goodsId);
	}
	
	public List<Goods> getByUserId(int userId){
		return gd.getByUserId(userId);
	}
}
