package com.gem.erhuo.dao.test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.Test;

import com.gem.erhuo.dao.BaseDaoImpl;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.util.DBConnection;

public class BaseDaoImplTest {
	BaseDaoImpl bdi = new BaseDaoImpl();

	// BaseDaoImpl的save()方法测试
	@SuppressWarnings("unchecked")
	@Test
	public void saveTest() throws Exception {
		Users u = new Users();
		u.setIdentity("15071073594");
		u.setPwd("abcd1234");
		File photo = new File("E:\\photo.jpg");
		u.setPhoto(null);
		u.setName("王lius");
		u.setSex(1);
		u.setJifen(200);
		u.setInvCode("94444");
		bdi.save(u);
	}

	// BaseDaoImpl的delete()方法测试
	@SuppressWarnings("unchecked")
	@Test
	public void deleteTest() throws Exception {
		bdi.delete(new int[]{6,10}, new Users());
	}
	
	// BaseDaoImpl的update()方法测试
	@Test
	public void updateTest(){
		
	}
	
	// BaseDaoImpl的getAll()方法测试
	@Test
	public void getAllTest() throws Exception{
			List l = bdi.getAll(new Users());
			System.out.println(l.toString());
	}
	// BaseDaoImpl的getAll()方法测试
	@Test
	public void getCountTest() throws Exception{
			int count= bdi.getCount(new Users());
			System.out.println(count);
	}
}
