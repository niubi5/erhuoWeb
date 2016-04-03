package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.service.GetDonatorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetDonatorServlet
 */
public class GetDonatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDonatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // 设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		
	    int helpId = Integer.valueOf(request.getParameter("helpId"));
	    
	    GetDonatorService gds = new GetDonatorService();
	    List<String> names = gds.getName(helpId);
//	    List<Map<Integer,List<String>>> donatorsName= new ArrayList<Map<Integer,List<String>>>();
//	    Map<Integer,List<String>> is = new HashMap<Integer,List<String>>();
//	    is.put(helpId, names);
//	    donatorsName.addAll(donatorsName);
	    for(int i = 0;i < names.size();i++){
	    	System.out.println(names.get(i));
	    }
	    // 将取得的userName发送到客户端
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	    String donators = gson.toJson(names);
	    
	    PrintWriter pw = response.getWriter();
	    pw.print(donators);
	    pw.close();
	}

}
