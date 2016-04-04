package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.service.AddressService;
import com.gem.erhuo.service.OrderService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetAddressServlet
 */
public class GetAddressServlet extends HttpServlet {
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
		String addressId = request.getParameter("addressId");
		System.out.println(addressId);
		AddressService as = new AddressService();
		/**
		 * @heikki  04.02 22:45
		 * */
		Address add = as.getUserAddressById(Integer.parseInt(addressId));
		Gson gson = new Gson();
		String addJson = gson.toJson(add);
		PrintWriter pw = response.getWriter();
		pw.print(addJson);
		System.out.println("地址："+addJson);
		pw.flush();
		pw.close();
	}

}
