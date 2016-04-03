package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.HelpsDao;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Helps;

public class HelpsService {

	HelpsDao helpsDao = new HelpsDao();

	// 保存信息
	public int saveHelps(Helps helps) {
		return helpsDao.save(helps);
	}

	// 获得所有请求捐赠信息
	public List<Helps> getAll(Helps helps) {
		return helpsDao.getAll(helps);
	}

	// 获得分页商品
	public List<Helps> getPagedHelps(int curPage, int pageSize) {
		return helpsDao.getPagedHelps(curPage, pageSize);
	}
	
	// 获得用户分页商品
		public List<Helps> getUserPagedHelps(int curPage, int pageSize,int userId) {
			return helpsDao.getUserPagedHelps(curPage, pageSize,userId);
		}
		
		//根据id获得求助信息
		public Helps getHelpsById(int id){
			return helpsDao.getByID(new Helps(), id);
		}
		//删除
		public void deleteHelps(int[] ids){
			helpsDao.delete(ids, new Helps());
		}

}
