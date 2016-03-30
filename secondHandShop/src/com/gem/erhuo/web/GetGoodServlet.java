package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetGoodServlet
 */
public class GetGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int goodId = Integer.parseInt(request.getParameter("goodId"));
		GoodsService gs = new GoodsService();
		GoodsImagesService gis = new GoodsImagesService();
		// 获得商品集合
		Map<Goods, List<String>> mapGoods = new HashMap<Goods, List<String>>();
		// 取出id
		Goods goods = gs.getGoodsById(goodId);
		// 获得url集合
		List<String> urls = gis.getGoodsImages(goods.getId());
		// 将商品，图片集合封装
		mapGoods.put(goods, urls);
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(mapGoods);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
