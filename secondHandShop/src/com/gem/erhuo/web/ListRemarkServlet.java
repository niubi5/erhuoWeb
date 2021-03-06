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

import com.gem.erhuo.entity.Remark;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.RemarkService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ListRemarkServlet
 */
public class ListRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListRemarkServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		RemarkService rs = new RemarkService();
		UserService us = new UserService();
		List<Remark> listRemark = rs.getAll(goodsId);
		List<Map<Remark, Users>> listRemarkUsers = new ArrayList<Map<Remark, Users>>();
		// 通过评论中的用户id取出用户对象，封装起来
		for (Remark remark : listRemark) {
			Users user = us.getById(remark.getUserId());
			System.out.println(remark.getUserId());
			System.out.println(user.getPhoto());
			Map<Remark, Users> userRemark = new HashMap<Remark, Users>();
			userRemark.put(remark, user);
			listRemarkUsers.add(userRemark);// 加到list中
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String str = gson.toJson(listRemarkUsers);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();

	}

}
