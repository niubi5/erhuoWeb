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

/**
 * Servlet implementation class UserInformationServlet
 */
public class UserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		UserService service=new UserService();
		service.updateUser(name, phone, sex);
		Users users=service.getUserByIdentity(phone);
		String userStr = null;
		if(users != null){
			userStr = new Gson().toJson(users);
		}
		PrintWriter pw = response.getWriter();
		pw.print(userStr);
		System.out.println(userStr);
		pw.close();
		
	}

}

