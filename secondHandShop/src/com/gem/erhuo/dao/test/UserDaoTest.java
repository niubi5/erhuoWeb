package com.gem.erhuo.dao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.gem.erhuo.dao.UserDao;
import com.gem.erhuo.entity.Users;

//UserDao的测试类
public class UserDaoTest {
	UserDao ud = new UserDao();
	//测试UserDao中的save()方法
	@Test
	public void saveTest(){
		Users u = new Users();
		u.setIdentity("15071073594");
		u.setPwd("abcd1234");
		File photo = new File("E:\\photo.jpg");
		u.setPhoto(null);
		u.setName("王六");
		u.setSex(1);
		u.setJifen(200);
		u.setInvCode("94444");
		ud.save(u);
	}
	//测试UserDao中的getUserByIdentity()方法
	@Test
	public void getUserByIdentity() {
		System.out.println(ud.getUserByIdentity("15071048315").getPwd());
	}
}
