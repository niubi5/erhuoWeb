package com.gem.erhuo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;

/**
 * Servlet implementation class ListUserServlet
 */
public class ListUserServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		int PAGE_SIZE = 0;
		//显示第几页的学生
		String spage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		int page = 1;
		if(spage!=null){
			page = Integer.parseInt(spage);
		}
		if(pageSize!=null){
			PAGE_SIZE = Integer.parseInt(pageSize);
		}
		//1、从数据库中取数据
		UserService service = new UserService();
		List<Users> lu = service.getPage(new Users(),page, PAGE_SIZE);
		int counts = service.getCount(new Users()); //总个数
		//共有多少页，每页显示的显示的个数有关
		int pages = 0;
		
		if(counts % PAGE_SIZE == 0){
			pages = counts/PAGE_SIZE;
		}else{
			pages = counts/PAGE_SIZE+1;
		}
		//2、存
//		for(Users u:lu){
//			System.out.println(u.getName());
//		}
		request.setAttribute("listuser", lu);
		request.setAttribute("counts", counts);
		request.setAttribute("pages", pages);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", PAGE_SIZE);
		//3、list.jsp
		request.getRequestDispatcher("/erhuo/user/list.jsp").forward(request, response);
	}

}
