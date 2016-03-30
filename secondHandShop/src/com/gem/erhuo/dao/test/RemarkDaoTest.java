package com.gem.erhuo.dao.test;

import org.junit.Test;

import com.gem.erhuo.dao.RemarkDao;
import com.gem.erhuo.entity.Remark;

public class RemarkDaoTest {

	RemarkDao rd = new RemarkDao();
	
	@Test
	public void getAllTest(){
		rd.getAll(1);
	}
	
	@Test
	public void saveConmment(){
		Remark remark = new Remark();
		remark.setGoodsId(1);
		remark.setUserId(14);
		remark.setComment_content("测试！！");
		remark.setComment_time("2016-03-30 08:41:20");
//		remark.setFatherId(0);
		remark.setIsEnd(0);
		rd.saveRemark(remark);
	}
}
