package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.service.GoodsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GoodsServlet
 */
public class ListGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		BaseDaoImpl<Goods> baseDao = new BaseDaoImpl<Goods>();
//		Goods good = baseDao.getByID(new Goods(), 1);
//		Gson gson = new GsonBuilder()
//				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		String json = gson.toJson(good);
//		PrintWriter pw = response.getWriter();
//		pw.print(json);
//		pw.close();
		// 获得商品集合
		GoodsService gs = new GoodsService();
		List<Goods> listGoods = gs.getAllGoods(new Goods());
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String str = gson.toJson(listGoods);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.close();
		// 遍历集合取出id
		// 通过id查找商品图片表，取出图片url，将url封装
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
