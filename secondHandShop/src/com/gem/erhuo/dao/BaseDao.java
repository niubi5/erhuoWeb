package com.gem.erhuo.dao;

import java.util.List;

public interface BaseDao<T> {
	//存储
	public void save(T t) throws Exception;
	//删除
	public void delete(int[] ids,T t) throws Exception;
	//修改
	public void update(T t) throws Exception;
	//查询所有记录
	public List<T> getAll(T t) throws Exception;
	//根据id查询
	public T getByID(T t,int id) throws Exception;
	//分页查询
	public List<T> getPage(T t,int curPage,int pageSize) throws Exception;
	//查询所有个数
	public int getCount(T t) throws Exception;
}
