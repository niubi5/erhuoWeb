package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.UserMarketDao;

public class UserMarketService {
	
	private UserMarketDao umd = new UserMarketDao();

	public void saveUserMarket(int userId, int marketId){
		umd.saveUserMarket(userId, marketId);
	}
	
	public void deleteUserMarket(int userId, int marketId){
		umd.deleteUserMarket(userId, marketId);
	}

	public List<Integer> getUserMarketIDById(int userId) {
		return umd.getUserMarketIDById(userId);
	}
}
