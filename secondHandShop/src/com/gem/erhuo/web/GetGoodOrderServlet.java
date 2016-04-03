package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.service.OrderService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetGoodOrderServlet
 */
public class GetGoodOrderServlet extends HttpServlet {
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
		String goodId = request.getParameter("goodId");
		System.out.println(goodId);
		OrderService os = new OrderService();
		Orders order = os.getOrderByGoodId(Integer.parseInt(goodId));
		Gson gson = new Gson();
		String orderJson = gson.toJson(order);
		PrintWriter pw = response.getWriter();
		pw.print(orderJson);
		System.out.println("订单："+orderJson);
		pw.flush();
		pw.close();
	}

}
