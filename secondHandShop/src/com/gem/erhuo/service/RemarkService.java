package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.RemarkDao;
import com.gem.erhuo.entity.Remark;

public class RemarkService {

	RemarkDao rd = new RemarkDao();
	
	public List<Remark> getAll(int goodsId){
		List<Remark> list = rd.getAll(goodsId);
		return list;
	}
	
	public void saveRemark(Remark remark){
		rd.saveRemark(remark);
	}
}
