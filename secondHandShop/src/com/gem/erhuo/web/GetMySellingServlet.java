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
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class GetMySellingServlet
 */
public class GetMySellingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		GoodsService gs = new GoodsService();
		GoodsImagesService gis = new GoodsImagesService();
		// 获得用户已售出商品集合
		List<Goods> listGoods = gs.getSellingPagedGoods(curPage, pageSize, userId);
		//System.out.println(listGoods.toString());
		List<Map<Goods, List<String>>> listSellingGood = new ArrayList<Map<Goods, List<String>>>();
		// Map<Goods,List<String>>
		// 遍历集合取出id
		// 通过id查找商品图片表，取出图片url，将url封装
		for (Goods goods : listGoods) {
			Map<Goods, List<String>> mapGood = new HashMap<Goods, List<String>>();
			// 获得url集合
			List<String> urls = gis.getGoodsImages(goods.getId());
			// 将商品，图片集合封装
			// mapAll.put(goodsUsers, urls);
			// 加入总集合
			mapGood.put(goods, urls);
			listSellingGood.add(mapGood);
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(listSellingGood);
		PrintWriter pw = response.getWriter();
		System.out.println(str);
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
