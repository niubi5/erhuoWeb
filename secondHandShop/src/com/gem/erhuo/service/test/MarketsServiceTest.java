package com.gem.erhuo.service.test;

import java.util.List;

import org.junit.Test;

import com.gem.erhuo.entity.Markets;
import com.gem.erhuo.service.MarketsService;

public class MarketsServiceTest {
	
	MarketsService ms = new MarketsService();
	
	@Test
	public List<Markets> getAllTest(Markets market){
		return ms.getAll(market);
	}

}
