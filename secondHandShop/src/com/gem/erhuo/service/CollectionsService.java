package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.CollectionsDao;

public class CollectionsService {
	CollectionsDao cd = new CollectionsDao();
	
	public void save(int userId, int goodsId){
		cd.saveCollection(userId, goodsId);
	}
	
	public void delete(int userId, int goodsId){
		cd.deleteCollection(userId, goodsId);
	}
	
	public List<Integer> getCollection(int userId){
		return cd.getCollection(userId);
	}
}
