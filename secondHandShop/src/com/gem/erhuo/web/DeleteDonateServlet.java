package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.service.AddressService;
import com.gem.erhuo.service.DonateService;
import com.google.gson.Gson;

/**
 * Servlet implementation class DeleteDonateServlet
 */
public class DeleteDonateServlet extends HttpServlet {
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
		String deleteDonatesId = request.getParameter("deleteDonatesId");
		System.out.println(deleteDonatesId);
		DonateService ds = new DonateService();
		/**
		 * @heikki  04.02 22:45
		 * */
		int[] donateIds = new int[]{Integer.parseInt(deleteDonatesId)};
		ds.deleteDonate(donateIds);
		PrintWriter pw = response.getWriter();
		pw.print("ok");
		System.out.println("删除捐赠");
		pw.flush();
		pw.close();
	}

}
