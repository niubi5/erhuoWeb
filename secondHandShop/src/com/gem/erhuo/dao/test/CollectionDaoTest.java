package com.gem.erhuo.dao.test;

import java.util.Date;

import org.junit.Test;

import com.gem.erhuo.dao.CollectionDao;
import com.gem.erhuo.entity.Collections;

public class CollectionDaoTest {
	@Test
	public void save() {
		Collections c = new Collections();
		c.setUserId(1);
		c.setGoodId(2);
		c.setColTime(new Date(System.currentTimeMillis()));
		new CollectionDao().save(c);
	}
}
