package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.GoodService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddGoodServlet
 */
public class AddGoodServlet extends HttpServlet {
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
		//response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
//		String userId = request.getParameter("userId");
//		String name = request.getParameter("name");
//		String imformation = request.getParameter("imformation");
//		String typeId = request.getParameter("typeId");
//		String soldPrice = request.getParameter("soldPrice");
//		String buyPrice = request.getParameter("buyPrice");
//		String marketId = request.getParameter("marketId");
//		String longitude = request.getParameter("longitude");
//		String latitude = request.getParameter("latitude");
//		String pubTime = request.getParameter("pubTime");
//		String state = request.getParameter("state");
//		Goods good = new Goods();
//		good.setUserId(Integer.parseInt(userId));
//		good.setName(name);
//		good.setImformation(imformation);
//		good.setTypeId(Integer.parseInt(typeId));
//		good.setSoldPrice(Double.parseDouble(soldPrice));
//		good.setBuyPrice(Double.parseDouble(buyPrice));
//		good.setMarketId(Integer.parseInt(marketId));
//		good.setLongitude(Double.parseDouble(longitude));
//		good.setLatitude(Double.parseDouble(latitude));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		try {
//			good.setPubTime(sdf.parse(pubTime));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		good.setState(Integer.parseInt(state));
		String goodJson = request.getParameter("goodJson");
		System.out.println(goodJson);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
		Goods good = gson.fromJson(goodJson,Goods.class);
		GoodService gs = new GoodService();
		System.out.println(gs.save(good));
		PrintWriter pw = response.getWriter();
		//pw.write(c);
	}

}
