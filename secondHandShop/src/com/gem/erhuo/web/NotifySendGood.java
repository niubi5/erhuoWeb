package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.api.JPush;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.service.GoodsService;

/**
 * Servlet implementation class NotifySendGood
 */
public class NotifySendGood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int goodId = Integer.parseInt(request.getParameter("goodId"));
		GoodsService gs = new GoodsService();
		Goods good = gs.getGoodsById(goodId);
		// 发送推送消息
		JPush.TITLE = "发货通知";
		JPush.ALERT = "您上架的商品被拍下啦，尽快给买家发货哦!";
		JPush.ALIAS = new String[] { good.getUserId() + "" };
		JPush.sendPush();
	}

}
