package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
//		String identity = request.getParameter("identity");
//		String name = request.getParameter("name");
//		String pwd = request.getParameter("pwd");
		String userJson = request.getParameter("userJson");
		System.out.println(userJson);
		Gson gson = new Gson();
		Users user = gson.fromJson(userJson, Users.class);
		String identity = user.getIdentity();
		String name = user.getName();
		String pwd = user.getPwd();
		if(identity != null){
			UserService us = new UserService();
			boolean b = us.canRegister(identity);
			System.out.println(identity+name);
			if(b){
				us.save(user);
				request.setAttribute("tag",true);
			}else{
				request.setAttribute("identity",identity);
				request.setAttribute("name",name);
				request.setAttribute("pwd",pwd);			
			}		
		}
		request.getRequestDispatcher("/erhuo/user/add.jsp").forward(request, response);
	}

}
