package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.UserDao;
import com.gem.erhuo.entity.Users;

public class UserService {
	private UserDao ud = new UserDao();
	//保存用户
	public void save(Users user){
		ud.save(user);
	}
	public Users getById(int id){
		return ud.getByID(new Users(), id);
	}
	//删除用户
	public void delete(String[] ids, Users user){
		int[] newIds = new int[ids.length];
		for(int i = 0;i<ids.length;i++){
			newIds[i] = Integer.parseInt(ids[i]);
		}
		ud.delete(newIds, user);
	}
	//获得分页用户
	public List<Users> getPage(Users user, int curPage, int pageSize){	
		return ud.getPage(user, curPage, pageSize);
	}
	//获得用户总数
	public int getCount(Users user) {
		return ud.getCount(user);
	}
	//判断用户账号是否存在，是否可注册
	public boolean canRegister(String identity){
		if(ud.getUserByIdentity(identity) == null) return true;
		return false;
	}
	//判断用户是否可以登录
	public Users canLogin(String identity,String pwd){
		if(ud.getUserByIdentity(identity) == null) return null;
		Users u = ud.getUserByIdentity(identity);
		if(u.getPwd().equals(pwd)){
			return u;
		}else{
			return null;
		}
	}
	//修改密码
		public void update(String phone,String pwd){
			ud.update(phone,pwd);
		}
}
