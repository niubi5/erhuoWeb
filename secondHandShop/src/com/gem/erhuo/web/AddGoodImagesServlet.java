package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class addGoodImagesServlet
 */
public class AddGoodImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @author heikki 2016.03.19
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收并保存客户端上传的图片
		//设置字符编码，防止乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("textml;charset=utf-8");
		//使用SmartUpload来处理上传的图片
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			String realpath = this.getServletContext().getRealPath("GoodsImages");
			File imageDir = new File(realpath);
			//如果目录不存在则先创建该目录
			if(!imageDir.exists()){
				imageDir.mkdirs();
				System.out.println("创建"+imageDir+"文件夹...");
			}
			System.out.println("接收到到客户端上传的图片个数:"+smartUpload.getFiles().getCount());
			for(int i = 0;i<smartUpload.getFiles().getCount();i++){
				com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(i);
				if(!poster.isMissing()){
					File file = new File(getServletContext().getRealPath("GoodsImages"),poster.getFileName());
					String saveImageName = file.getAbsolutePath();
					//文件的保存路径
					poster.saveAs(saveImageName);
				}
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
