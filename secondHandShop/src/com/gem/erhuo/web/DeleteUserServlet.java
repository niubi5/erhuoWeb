package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
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
		//获得数据，要删除的用户的ID，多个ID
		String ids[] = request.getParameterValues("checkUser");
		System.out.println(ids.toString());
		UserService us = new UserService();
		us.delete(ids,new Users());
		request.getRequestDispatcher("/ListUserServlet?curPage=1&pageSize=10").forward(request, response);
	}

}
