package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.HelpsImagesDao;
import com.gem.erhuo.entity.HelpsImages;

public class HelpsImagesService {
	HelpsImagesDao helpsImagesDao = new HelpsImagesDao();
	
	// 保存图片
	public int save(HelpsImages helpsImages){
		return helpsImagesDao.save(helpsImages);
	}

	// 获得所有求助图片url
	public List<String> getHelpsImages(int helpsId){
		return helpsImagesDao.getGoodsImagesUrl(helpsId);
	}
}
