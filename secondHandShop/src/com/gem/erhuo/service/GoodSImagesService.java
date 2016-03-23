package com.gem.erhuo.service;

import com.gem.erhuo.dao.GoodsImagesDao;
import com.gem.erhuo.entity.GoodsImages;

public class GoodSImagesService {
	private GoodsImagesDao gid = new GoodsImagesDao();
	//保存商品图片
	public int save(GoodsImages gi){
		return gid.save(gi);
	}
}
