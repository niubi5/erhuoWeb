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
	
	public Remark getRemarkById(int remarkId){
		return rd.getRemarkById(remarkId);
	}
	
	// 将remark的is_end设为1
	public void update(int id){
		rd.update(id);
	}
	
	public List<Remark> getAllRemarkByUserId(int userid){
		return rd.getAllRemarkByUserId(userid);
	}
	
	public List<Remark> getAllByFatherId(int fatherId){
		return rd.getAllByFatherID(fatherId);
	}
	
	public List<Remark> getMyReceiveRemarks(int userId, int curPage, int pageSize){
		return rd.getMyReceiveRemarks(userId, curPage, pageSize);
	}
	
}
