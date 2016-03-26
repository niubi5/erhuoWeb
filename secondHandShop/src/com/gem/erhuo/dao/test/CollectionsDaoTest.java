package com.gem.erhuo.dao.test;

import org.junit.Test;

import com.gem.erhuo.dao.CollectionsDao;

public class CollectionsDaoTest {

	CollectionsDao cd = new CollectionsDao();
	@Test
	public void saveTest(){
		cd.saveCollection(1,2);
	}
}
