package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import com.gem.erhuo.service.GoodService;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchGoodsServlet
 */
public class SearchGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String word=new String(request.getParameter("word").getBytes("iso-8859-1"),"utf-8");
		//String word=URLDecoder.decode(request.getParameter("word"), "gbk");
		System.out.println("word:"+word);
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		//获得商品集合
		GoodService service=new GoodService();
		UserService us = new UserService();
		GoodsImagesService gis = new GoodsImagesService();
		List<Goods> listGoods=service.getGoodsList(word,curPage,pageSize);
		List<Map<Map<Goods, Users>, List<String>>> listAll = new ArrayList<Map<Map<Goods, Users>, List<String>>>();
		// 遍历集合取出id
	    // 通过id查找商品图片表，取出图片url，将url封装
		for (Goods goods : listGoods) {
			// 获得url集合
			List<String> urls = gis.getGoodsImages(goods.getId());
			// 通过id获得用户对象
			Users user = us.getById(goods.getUserId());
			Map<Map<Goods, Users>, List<String>> mapAll = new HashMap<Map<Goods, Users>, List<String>>();
			Map<Goods, Users> goodsUsers = new HashMap<Goods, Users>();
			// 将商品，用户加入集合
			goodsUsers.put(goods, user);
			// 将商品，用户，图片集合封装
			mapAll.put(goodsUsers, urls);
			// 加入总集合
			listAll.add(mapAll);
		}
		
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(listAll);
		
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取传来的参数
		doGet(request, response);
//		
//		String word=request.getParameter("word");
//		int curPage = Integer.parseInt(request.getParameter("curPage"));
//		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//		//获得商品集合
//		GoodService service=new GoodService();
//		UserService us = new UserService();
//		GoodsImagesService gis = new GoodsImagesService();
//		List<Goods> listGoods=service.getGoodsList(word,curPage,pageSize);
//		List<Map<Map<Goods, Users>, List<String>>> listAll = new ArrayList<Map<Map<Goods, Users>, List<String>>>();
//		// 遍历集合取出id
//	    // 通过id查找商品图片表，取出图片url，将url封装
//		for (Goods goods : listGoods) {
//			// 获得url集合
//			List<String> urls = gis.getGoodsImages(goods.getId());
//			// 通过id获得用户对象
//			Users user = us.getById(goods.getUserId());
//			Map<Map<Goods, Users>, List<String>> mapAll = new HashMap<Map<Goods, Users>, List<String>>();
//			Map<Goods, Users> goodsUsers = new HashMap<Goods, Users>();
//			// 将商品，用户加入集合
//			goodsUsers.put(goods, user);
//			// 将商品，用户，图片集合封装
//			mapAll.put(goodsUsers, urls);
//			// 加入总集合
//			listAll.add(mapAll);
//		}
//		
//		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		String str = gson.toJson(listAll);
//		
//		PrintWriter pw = response.getWriter();
//		pw.print(str);
//		pw.close();
//		
	}

}
