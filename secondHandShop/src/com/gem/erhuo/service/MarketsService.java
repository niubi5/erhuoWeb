package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.MarketsDao;
import com.gem.erhuo.entity.Markets;

public class MarketsService {
	
	private MarketsDao md = new MarketsDao();
	
	public List<Markets> getAll(Markets market){
		return md.getAll(market);
	}
	
	public void marketGoodsCountPlus(int marketId){
		md.marketGoodsCountPlus(marketId);
	}
	
	public void marketUsersCountPlus(int marketId){
		md.marketUsersCountPlus(marketId);
	}
	
	public void marketUsersCountSub(int marketId){
		md.marketUsersCountSub(marketId);
	}
}
