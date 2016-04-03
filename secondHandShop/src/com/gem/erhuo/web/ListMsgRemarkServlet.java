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
 * Servlet implementation class CommentRemarkServlet
 */
public class ListMsgRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListMsgRemarkServlet() {
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
		int fatherId = Integer.parseInt(request.getParameter("fatherId"));
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		RemarkService rs = new RemarkService();
		UserService us = new UserService();
		List<Map<Remark, Users>> listRemarkUsers = new ArrayList<Map<Remark, Users>>();
		// 通过用户Id找到fatherid字段对应的所有Remark对象
		List<Remark> listRemarks = rs.getAllByFatherId(fatherId, curPage, pageSize);
		// 通过Remark对象的useId找到所有评论用户
		for (Remark remark : listRemarks) {
			// 将评论对象与该用户对象封装在一起
			Users user = us.getById(remark.getUserId());
			Map<Remark, Users> map = new HashMap<Remark, Users>();
			map.put(remark, user);
			// 加入list中（确保有序排列）
			listRemarkUsers.add(map);
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(listRemarkUsers);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();
	}

}
