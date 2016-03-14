package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;

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
		String identity = request.getParameter("identity");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		if(identity != null){
			UserService us = new UserService();
			boolean b = us.canRegister(identity);
			System.out.println(identity+name);
			if(b){
				Users u = new Users();
				u.setIdentity(identity);
				u.setName(name);
				u.setPwd(pwd);
				us.save(u);
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
