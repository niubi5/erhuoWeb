package com.gem.erhuo.service;

import com.gem.erhuo.dao.HelpsReportDao;
import com.gem.erhuo.entity.HelpsReports;

public class HelpsReportService {
	
	HelpsReportDao hpd = new HelpsReportDao();
	
	public int save(HelpsReports helpsReports)
	{
		return hpd.save(helpsReports);
	}

}
