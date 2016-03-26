package com.gem.erhuo.service;

import com.gem.erhuo.dao.GoodsReportsDao;
import com.gem.erhuo.entity.GoodsReports;

public class GoodsReportsService {
	private GoodsReportsDao grd = new GoodsReportsDao();

	public int save(GoodsReports gr) {
		return grd.save(gr);
	}
}
