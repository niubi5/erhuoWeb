package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.UserMarket;
import com.gem.erhuo.service.MarketsService;
import com.gem.erhuo.service.UserMarketService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserMarketServlet
 */
public class UserMarketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserMarketServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserMarketService ums = new UserMarketService();
		if (request.getParameter("marketId") != null 
				&& request.getParameter("flag") != null) {
			MarketsService ms = new MarketsService();
			int marketId = Integer.parseInt(request.getParameter("marketId"));
			int flag = Integer.parseInt(request.getParameter("flag"));
			if (flag == 0) {// 添加
				ums.saveUserMarket(userId, marketId);
				// 集市人数加一
				ms.marketUsersCountPlus(marketId);
			} else if (flag == 1) {// 删除
				ums.deleteUserMarket(userId, marketId);
				// 集市人数减一
				ms.marketUsersCountSub(marketId);
			}
		} else {
			// 查询所有
			List<Integer> list = ums.getUserMarketIDById(userId);
			PrintWriter pw = response.getWriter();
			Gson gson = new Gson();
			String str = gson.toJson(list);
			pw.println(str);
			pw.close();
		}

	}

}
