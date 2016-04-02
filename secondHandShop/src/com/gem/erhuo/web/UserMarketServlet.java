package com.gem.erhuo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.service.UserMarketService;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int marketId = Integer.parseInt(request.getParameter("marketId"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		UserMarketService ums = new UserMarketService();
		if(flag == 0){// 添加
			ums.saveUserMarket(userId, marketId);
		} else if (flag == 1){// 删除
			ums.deleteUserMarket(userId, marketId);
		}
	}

}
