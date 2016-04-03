package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.GetDonatorNameDao;

public class GetDonatorService {
	
	GetDonatorNameDao dao = new GetDonatorNameDao();
	
	// 获得捐赠人的集合
	public List<String> getName(int helpId){
		return dao.getUserName(helpId);
	}

}
