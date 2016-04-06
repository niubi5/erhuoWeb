package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.UserService;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

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
		try {
			smartUpload.upload();
			String userid=smartUpload.getRequest().getParameter("userId");
			System.out.println("userid:"+userid);
			UserService service=new UserService();
			Users users= service.getById(Integer.parseInt(userid));
			String realpath = this.getServletContext().getRealPath("headerimages");
			File imageDir = new File(realpath.substring(0, realpath.indexOf("secondHandShop"))+"\\ROOT\\headerimages");
			//如果目录不存在则先创建该目录
			if(!imageDir.exists()){
				imageDir.mkdirs();
				System.out.println("创建"+imageDir+"文件夹...");
			}
			System.out.println("接收到到客户端上传的图片个数:"+smartUpload.getFiles().getCount());
			for(int i = 0;i<smartUpload.getFiles().getCount();i++){
				com.jspsmart.upload.File poster = smartUpload.getFiles().getFile(i);
				if(!poster.isMissing()){
					//客户端传过来的图片名
					String imageName = poster.getFileName();
					File file = new File(imageDir,""+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					//文件的保存路径
					String saveImageName = file.getAbsolutePath();
					saveImageName.substring(saveImageName.lastIndexOf(""));
					System.out.println("文件绝对路径：" + saveImageName);
					try {
						poster.saveAs(saveImageName);
						//将存在本地的图片路径写入用户表
						users.setPhoto((saveImageName.substring(saveImageName.indexOf("headerimages"))).replace('\\', '/'));
					    service.update(users);
					} catch (SmartUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				}
				
			}
		} catch (SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
