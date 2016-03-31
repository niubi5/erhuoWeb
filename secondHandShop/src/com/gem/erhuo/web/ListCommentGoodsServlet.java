package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Remark;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ListCommentGoodsServlet
 */
public class ListCommentGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCommentGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("listRemarks");
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Type type = new TypeToken<List<Remark>>(){}.getType();
		List<Remark> listRemarks = gson.fromJson(str, type);
		GoodsService gs = new GoodsService();
		GoodsImagesService gis = new GoodsImagesService();
		UserService us = new UserService();
		List<Map<Map<Goods, Users>, List<String>>> listAll = new ArrayList<Map<Map<Goods, Users>, List<String>>>();
		// 通过userId找到用户
		for(Remark remark : listRemarks){
			Users user = us.getById(remark.getUserId());
			// 通过goodsId找到商品
			Goods goods = gs.getGoodsById(remark.getGoodsId());
			// 通过goodsId找到Url集合
			List<String> listUrls = gis.getGoodsImages(remark.getGoodsId());
			Map<Map<Goods, Users>, List<String>> mapAll = new HashMap<Map<Goods, Users>, List<String>>();
			Map<Goods, Users> goodsUsers = new HashMap<Goods, Users>();
			goodsUsers.put(goods, user);
			mapAll.put(goodsUsers, listUrls);
			listAll.add(mapAll);
		}
		String str1 = gson.toJson(listAll);
		PrintWriter pw = response.getWriter();
		pw.print(str1);
		pw.close();
	}

}
