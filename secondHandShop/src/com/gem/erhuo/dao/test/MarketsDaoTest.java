package com.gem.erhuo.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.erhuo.dao.MarketsDao;
import com.gem.erhuo.entity.Markets;

public class MarketsDaoTest {

	MarketsDao md = new MarketsDao();
	Markets market = new Markets();
	@Test
	public void getAllTest(){
		System.out.println(md.getAll(new Markets()).toString());
	}
}
