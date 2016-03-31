package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.DonateDao;
import com.gem.erhuo.entity.Donates;

public class DonateService {
	private DonateDao dd = new DonateDao();

	public List<Donates> getUserDonate(int curPage, int pageSize, int userId) {
		return dd.getUserDonate(curPage, pageSize, userId);
	}
}
