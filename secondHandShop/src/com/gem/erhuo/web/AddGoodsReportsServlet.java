package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.GoodsReports;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.service.GoodsReportsService;
import com.gem.erhuo.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddGoodsReportsServlet
 */
public class AddGoodsReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码，防止乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("AddGoodOrderServlet");
		GoodsReportsService grs = new GoodsReportsService();
		// 获取商品举报信息
		String goodsReportJson = request.getParameter("goodsReportJson");
		System.out.println(goodsReportJson);
		Gson goodsReportGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		GoodsReports gr = goodsReportGson.fromJson(goodsReportJson, GoodsReports.class);
		System.out.println(grs.save(gr));
	}

}
