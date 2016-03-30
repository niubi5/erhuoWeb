package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Markets;
import com.gem.erhuo.service.MarketsService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ListMarketsServlet
 */
public class ListMarketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMarketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		MarketsService ms = new MarketsService();
		List<Markets> list = ms.getAll(new Markets());
		Gson gson = new Gson();
		String str = gson.toJson(list);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();
	}

}
