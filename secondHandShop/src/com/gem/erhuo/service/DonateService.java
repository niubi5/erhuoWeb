package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.DonateDao;
import com.gem.erhuo.entity.Donates;

public class DonateService {
	private DonateDao dd = new DonateDao();
	
	public void save(Donates donates){
		dd.save(donates);
	}

	public List<Donates> getUserOrders(int curPage, int pageSize, int userId) {
		return dd.getUserDonate(curPage, pageSize, userId);
	}
}
