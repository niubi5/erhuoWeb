package com.gem.erhuo.service;

import com.gem.erhuo.dao.HelpsDao;
import com.gem.erhuo.entity.Helps;

public class HelpsService {
	
	HelpsDao helpsDao = new HelpsDao();
	
	// 保存信息
	public int saveHelps(Helps helps){
		return helpsDao.save(helps);
	}

}
