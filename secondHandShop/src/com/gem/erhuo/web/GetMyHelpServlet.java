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

import com.gem.erhuo.entity.Donates;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.service.DonateService;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodsService;
import com.gem.erhuo.service.HelpsImagesService;
import com.gem.erhuo.service.HelpsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetMyHelpServlet
 */
public class GetMyHelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得参数
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		HelpsService hs = new HelpsService();
		HelpsImagesService his = new HelpsImagesService();
		// 获得用户在售商品集合
		List<Helps> listHelps = hs.getUserPagedHelps(curPage, pageSize, userId);
		// Map<Helps,List<String>>
		List<Map<Helps, List<String>>> listHelpsPhoto = new ArrayList<Map<Helps, List<String>>>();
		// 遍历集合取出id
		// 通过id查找求助图片表，取出图片url，将url封装
		for (Helps helps : listHelps) {
			Map<Helps, List<String>> mapHelp = new HashMap<Helps, List<String>>();
			// 获得url集合
			List<String> urls = his.getHelpsImages(helps.getId());
			// 加入总集合
			mapHelp.put(helps, urls);
			listHelpsPhoto.add(mapHelp);
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(listHelpsPhoto);
		PrintWriter pw = response.getWriter();
		System.out.println(str);
		pw.print(str);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
