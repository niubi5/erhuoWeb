package com.gem.erhuo.service.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;

public class UserServiceTest {
	UserService us = new UserService();
	//测试保存用户
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
		us.save(u);
	}
	//测试获得分页用户
	@Test
	public void getPageTest(){	
		List<Users> list =  us.getPage(new Users(), 1, 10);
		for(Users u:list){
			System.out.println(u.getName());
		}
	}
	//测试获得用户个数
	@Test
	public void getCountTest() {
		System.out.println(us.getCount(new Users()));
	}
}
