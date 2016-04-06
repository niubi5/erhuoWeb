package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.service.GoodsService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateGoodServlet
 */
public class UpdateGoodServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		String newGoods = request.getParameter("newGoods");
		System.out.println(newGoods);
		Goods good = new Gson().fromJson(newGoods, Goods.class);
		GoodsService gs = new GoodsService();
		gs.updateGoods(good);
		Goods g = gs.getGoodsById(good.getId());
		Gson gson = new Gson();
		String str = gson.toJson(g);
		PrintWriter pw = response.getWriter();
		pw.print("str");
		pw.close();
		System.out.println(str);
	}

}
