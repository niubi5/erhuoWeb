package com.gem.erhuo.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Remark;
import com.gem.erhuo.service.RemarkService;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentServlet() {
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
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		RemarkService rs = new RemarkService();
		if (request.getParameter("id") != null) {
			// 修改父评论is_end
			rs.update(Integer.parseInt(request.getParameter("id")));
		}
		String commentContent = request.getParameter("commentContent");
		int fatherId = Integer.parseInt(request.getParameter("fatherId"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String commentTime = sdf.format(new Date());
		Remark remark = new Remark();
		// 封装成评论对象
		remark.setGoodsId(goodsId);
		remark.setUserId(userId);
		remark.setComment_content(commentContent);
		remark.setComment_time(commentTime);
		remark.setFatherId(fatherId);
		remark.setIsEnd(0);// 默认为终极评论
		rs.saveRemark(remark);
		
	}

}
