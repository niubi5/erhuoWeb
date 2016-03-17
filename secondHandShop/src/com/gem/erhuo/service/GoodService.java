package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GoodsDao;
import com.gem.erhuo.entity.Goods;

public class GoodService {
	private GoodsDao gd = new GoodsDao();
	//新增商品
	public void save(Goods goods){
		gd.save(goods);
	}
	//获得分页商品
	public List<Goods> getPage(Goods goods, int curPage, int pageSize){	
		return gd.getPage(goods, curPage, pageSize);
	}
}
