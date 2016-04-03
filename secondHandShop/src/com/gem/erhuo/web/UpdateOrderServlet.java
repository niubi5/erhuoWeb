package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.OrderService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateOrderServlet
 */
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String goodId = request.getParameter("goodId");
		String com = request.getParameter("com");
		String num = request.getParameter("num");
		String sendTime = request.getParameter("sendTime");
		System.out.println(goodId+","+com+","+num+","+sendTime);
		//修改订单状态
		OrderService os = new OrderService();
		Orders order = os.getOrderByGoodId(Integer.parseInt(goodId));
		order.setLogisticsCom(com);
		order.setLogisticsNum(num);
		order.setSendTime(sendTime);
		order.setState(3);
		os.updateOrder(order);
		//修改商品状态
		GoodsService gs = new GoodsService();
		Goods good = gs.getGoodsById(Integer.parseInt(goodId));
		good.setState(3);
		gs.update(good);
		PrintWriter pw = response.getWriter();
		pw.print("str");
		pw.close();
	}

}
