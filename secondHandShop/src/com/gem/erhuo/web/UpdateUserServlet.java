package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class UpdateUserServlet
 */
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doPost(request,response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usersphone=request.getParameter("phone");
		String userspwd=request.getParameter("pwd");
		String userStr = null;
		UserService us = new UserService();
		us.update(usersphone, userspwd);
		System.out.println("identity1:"+usersphone+"pwd1:"+userspwd);
		Users user =  us.canLogin(usersphone, userspwd);
		if(user != null){
			userStr = new Gson().toJson(user);
		}
		PrintWriter pw = response.getWriter();
		
		pw.print(userStr);
		
		pw.close();
	}

}
