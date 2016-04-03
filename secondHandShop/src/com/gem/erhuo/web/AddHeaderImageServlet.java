package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.GoodsImages;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class AddHeaderImageServlet
 */
public class AddHeaderImageServlet extends HttpServlet {
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
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		String realpath = this.getServletContext().getRealPath("headerimages");
		File imageDir = new File(realpath.substring(0, realpath.indexOf("secondHandShop"))+"\\ROOT\\headerimages");
		//如果目录不存在则先创建该目录
		if(!imageDir.exists()){
			imageDir.mkdirs();
			System.out.println("创建"+imageDir+"文件夹...");
		}
		for(int i = 0;i<smartUpload.getFiles().getCount();i++){
			com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(i);
			if(!poster.isMissing()){
				//客户端传过来的图片名
				String imageName = poster.getFileName();
				File file = new File(imageDir,""+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
				//文件的保存路径
				String saveImageName = file.getAbsolutePath();
				saveImageName.substring(saveImageName.lastIndexOf(""));
			//	poster.saveAs(saveImageName);
				//将存在本地的图片路径写入数据库图片表，并与对应的商品表的关联起来
				GoodsImages gi = new GoodsImages();
				gi.setUrl((saveImageName.substring(saveImageName.indexOf("headerimages"))).replace('\\', '/'));
			
				
			
			}
			
		}
		
		
	}

}
