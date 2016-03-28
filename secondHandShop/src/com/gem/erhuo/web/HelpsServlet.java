package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.entity.HelpsImages;
import com.gem.erhuo.service.HelpsImagesService;
import com.gem.erhuo.service.HelpsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class HelpsServlet
 */
public class HelpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelpsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
//		String name = request.getParameter("DonationRequest");
		// 使用SmartUpload来处理上传的图片
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			// 获取求助信息
			String helpsJson = smartUpload.getRequest().getParameter("DonationRequest");
//			 处理获得的求助的文字信息
			Gson gson = new GsonBuilder().create();
			Helps help = gson.fromJson(helpsJson, Helps.class);

			// 保存求助信息并返回当前求助id
			HelpsService helpsService = new HelpsService();
			int currentId = helpsService.saveHelps(help);
              
			HelpsImagesService his = new HelpsImagesService();
			// 处理获取的图片信息
			String realPath = this.getServletContext().getRealPath("helpsimages");
			File imageDir = new File(realPath.substring(0, realPath.indexOf("secondHandShop")) + "\\ROOT\\helpsimages");
			if(!imageDir.exists()){
				imageDir.mkdirs();
				System.out.println("创建"+imageDir+"文件夹...");
			}
			for(int i = 0 ; i<smartUpload.getFiles().getCount();i++){
				com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(i);
				if(!poster.isMissing()){
					// 客户端传过来的图片名
					String imageName = poster.getFileName();
					File file = new File(imageDir,""+currentId+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					//文件的保存路径
					String saveImageName = file.getAbsolutePath();
					saveImageName.substring(saveImageName.lastIndexOf(""));
					poster.saveAs(saveImageName);
					HelpsImages hi = new HelpsImages();
					hi.setHelpId(currentId);
					hi.setUrl((saveImageName.substring(saveImageName.indexOf("goodsimages"))).replace('\\', '/'));
					his.save(hi);
				}
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

}
