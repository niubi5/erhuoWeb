package com.gem.erhuo.service;

import com.gem.erhuo.dao.HelpsImagesDao;
import com.gem.erhuo.entity.HelpsImages;

public class HelpsImagesService {
	HelpsImagesDao helpsImagesDao = new HelpsImagesDao();
	
	// 保存图片
	public int save(HelpsImages helpsImages){
		return helpsImagesDao.save(helpsImages);
	}

}
