package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.service.DonateService;
import com.gem.erhuo.service.HelpsService;

/**
 * Servlet implementation class DeleteHelpServlet
 */
public class DeleteHelpServlet extends HttpServlet {
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
		String deleteHelpId = request.getParameter("deleteHelpId");
		System.out.println(deleteHelpId);
		HelpsService hs = new HelpsService();
		/**
		 * @heikki  04.02 22:45
		 * */
		int[] helpIds = new int[]{Integer.parseInt(deleteHelpId)};
		hs.deleteHelps(helpIds);
		PrintWriter pw = response.getWriter();
		pw.print("ok");
		System.out.println("删除求助");
		pw.flush();
		pw.close();
	}

}
