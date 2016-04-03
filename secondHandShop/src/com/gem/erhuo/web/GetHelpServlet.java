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

import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.service.HelpsImagesService;
import com.gem.erhuo.service.HelpsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetHelpServlet
 */
public class GetHelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得参数
		int helpId = Integer.parseInt(request.getParameter("helpId"));
		HelpsService hs = new HelpsService();
		HelpsImagesService his = new HelpsImagesService();
		// 获得id对应的help
		Helps help = hs.getHelpsById(helpId);
		// Map<help,List<String>>
		Map<Helps, List<String>> mapHelpsPhoto = new HashMap<Helps, List<String>>();
		// 通过id查找求助图片表，取出图片url，将url封装
		List<String> urls = his.getHelpsImages(help.getId());
		// 将help，图片集合封装
		mapHelpsPhoto.put(help, urls);
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(mapHelpsPhoto);
		PrintWriter pw = response.getWriter();
		System.out.println(str);
		pw.print(str);
		pw.close();
	}

}
