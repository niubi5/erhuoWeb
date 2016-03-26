package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.service.AddressService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserAddressServlet
 */
public class UserAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	      String phone=request.getParameter("phone");
	      AddressService as=new AddressService();
	      Address address=as.getUserAddressByPhoneService(phone);
	      String userAddress=null;
	      if(address!=null){
	    	  userAddress=new Gson().toJson(address);
	      }
	      PrintWriter pw=response.getWriter();
	      pw.print(userAddress);
	      pw.close();
	}

}
