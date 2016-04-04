package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Donates;
import com.gem.erhuo.service.DonateService;
import com.gem.erhuo.service.HelpsService;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetHelpAllDonateServlet
 */
public class GetHelpAllDonateServlet extends HttpServlet {
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
		String helpId = request.getParameter("helpId");
		System.out.println(helpId);
		DonateService ds = new DonateService();
		/**
		 * @heikki  04.02 22:45
		 * */
		List<Donates> listDonate = ds.getHelpDonate(Integer.parseInt(helpId));
		Gson gson = new Gson();
		String str = gson.toJson(listDonate);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		System.out.println(str);
		pw.flush();
		pw.close();
	}

}
