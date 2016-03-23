package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GoodsImagesDao;
import com.gem.erhuo.entity.GoodsImages;

public class GoodsImagesService {
	private GoodsImagesDao gid = new GoodsImagesDao();
	//保存商品图片
	public int save(GoodsImages gi){
		return gid.save(gi);
	}
	public List<String> getGoodsImages(int id) {
		return gid.getGoodsImagesUrl(id);
	}
}
