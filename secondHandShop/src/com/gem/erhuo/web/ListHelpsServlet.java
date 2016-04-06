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
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.HelpsImagesService;
import com.gem.erhuo.service.HelpsService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ListHelpsServlet
 */
public class ListHelpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListHelpsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 获得参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		HelpsService hs = new HelpsService();
		UserService us = new UserService();
		HelpsImagesService his = new HelpsImagesService();

		// 获得求助集合
		List<Helps> listHelps = hs.getPagedHelps(curPage, pageSize);
		// 封装所有查询到的信息
		List<Map<Map<Helps, Users>, List<String>>> listAll = new ArrayList<Map<Map<Helps, Users>, List<String>>>();

		// 遍历求助集合
		for (Helps help : listHelps) {
			// 根据helpId获得所有url，加入集合
			List<String> urls = his.getHelpsImages(help.getId());
			for (String url : urls) {
			}
			// 通过helpId获得用户对象
			Users user = us.getById(help.getUserId());
			Map<Map<Helps, Users>, List<String>> mapAll = new HashMap<Map<Helps, Users>, List<String>>();
			Map<Helps, Users> helpUsers = new HashMap<Helps, Users>();
			// 将求助，用户加入集合
			helpUsers.put(help, user);
			// 将求助，用户，图片集合封装
			mapAll.put(helpUsers, urls);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
