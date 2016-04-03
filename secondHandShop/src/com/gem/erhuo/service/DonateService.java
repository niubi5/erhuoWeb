package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.DonateDao;
import com.gem.erhuo.entity.Donates;

public class DonateService {
	private DonateDao dd = new DonateDao();
	//获得用户捐赠
	public List<Donates> getUserDonate(int curPage, int pageSize, int userId) {
		return dd.getUserDonate(curPage, pageSize, userId);
	}
	//删除用户捐赠
	public void deleteDonate(int[] donateIds){
		dd.delete(donateIds, new Donates());
	}
	//获得某个求助的所有捐赠
	public List<Donates> getHelpDonate(int helpId){
		return dd.getHelpDonate(helpId);
	}
}
