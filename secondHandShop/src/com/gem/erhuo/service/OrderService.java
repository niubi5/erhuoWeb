package com.gem.erhuo.service;

import java.util.List;

import com.gem.erhuo.dao.OrdersDao;
import com.gem.erhuo.entity.Orders;

public class OrderService {
	private OrdersDao od = new OrdersDao();
	//新增订单
	public int save(Orders order){
		return od.save(order);
	}
	//获得用户的订单
	public List<Orders> getUserOrders(int curPage,int pageSize,int userId){
		return od.getUserOrders(curPage, pageSize, userId);
	}
	//根据goodId找到order
	public Orders getOrderByGoodId(int goodId){
		return od.getOrderByGoodId(goodId);
	}
	//修改订单信息
	public void updateOrder(Orders order){
		od.update(order);
	}
}
