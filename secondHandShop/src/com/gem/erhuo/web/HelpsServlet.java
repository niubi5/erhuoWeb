package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.service.HelpsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class HelpsServlet
 */
public class HelpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelpsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// 使用SmartUpload来处理上传的图片
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			// 获取求助信息
			String helpsJson = smartUpload.getRequest().getParameter("DonationRequset");
			// 处理获得的求助的文字信息
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			Goods good = gson.fromJson(helpsJson, Helps.class);
			
			HelpsService helpsService = new HelpsService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
