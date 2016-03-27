package com.gem.erhuo.service;

import com.gem.erhuo.dao.OrdersDao;
import com.gem.erhuo.entity.Orders;

public class OrderService {
	private OrdersDao od = new OrdersDao();
	//新增订单
	public int save(Orders order){
		return od.save(order);
	}
}
