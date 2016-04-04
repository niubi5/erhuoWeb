package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.api.JPush;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddGoodOrderServlet
 */
public class AddGoodOrderServlet extends HttpServlet {
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
		//设置字符编码，防止乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("AddGoodOrderServlet");
		OrderService os = new OrderService();
		GoodsService gs = new GoodsService();
		//获取订单信息
		String orderJson = request.getParameter("orderJosn");
		System.out.println(orderJson);
		Gson orderGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Orders order = orderGson.fromJson(orderJson, Orders.class);
		Goods good = gs.getGoodsById(order.getGoodId());
		good.setState(2);
		gs.update(good);
		//发送推送消息
		JPush.TITLE = "订单通知";
		JPush.ALERT = "您上架的商品被拍下啦，赶快去看看吧!";
		JPush.ALIAS = new String[]{good.getUserId()+""};
		JPush.sendPush();
		System.out.println(os.save(order));
		
	}

}
