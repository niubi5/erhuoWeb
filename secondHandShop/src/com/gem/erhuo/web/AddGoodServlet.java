package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.api.JPush;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.MarketsService;
import com.gem.erhuo.service.GoodService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class AddGoodServlet
 */
public class AddGoodServlet extends HttpServlet {
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
		//设置字符编码，防止乱码
		
		
		//使用SmartUpload来处理上传的图片
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			//获取商品信息
			String goodJson = smartUpload.getRequest().getParameter("goodJson");
			//处理获得的商品的文字信息
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			Goods good = gson.fromJson(goodJson,Goods.class);
			
			GoodService gs = new GoodService();
//			if(){}
			GoodsImagesService gis = new GoodsImagesService();
			//保存商品文字信息，返回数据库自增长id
			int currentId = gs.save(good);
			if(good.getMarketId() != 0){
				// 将集市数量加一
				MarketsService ms = new MarketsService();
				ms.marketGoodsCountPlus(good.getMarketId());
				//向关注该集市的用户推送消息
				List<Integer> listUserId = ms.getMarketUserId(good.getMarketId());
				List<String> strUserId = new ArrayList<>();
				final int size =  listUserId.size();
				for(int i = 0; i < listUserId.size(); i ++){
					strUserId.add(listUserId.get(i).toString());
				}
				String[] arrUserId =  strUserId.toArray(new String[size]);
				JPush.TITLE = "新品上架";
				JPush.ALERT = "您关注的集市有新商品上架啦，赶快去看看吧!";
				JPush.ALIAS = arrUserId;
				JPush.sendPush();
				
			}
			//处理获得的图片信息
			String realpath = this.getServletContext().getRealPath("goodsimages");
			//String realpath = FileDirectory.getFileSaveDirectory()+"\\GoodsImages";
			File imageDir = new File(realpath.substring(0, realpath.indexOf("secondHandShop"))+"\\ROOT\\goodsimages");
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
					System.out.println(imageName);
					File file = new File(imageDir,""+currentId+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					//File file = new File(imageDir,""+currentId+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					//文件的保存路径
					String saveImageName = file.getAbsolutePath();
//					System.out.println(saveImageName);
					saveImageName.substring(saveImageName.lastIndexOf(""));
//					System.out.println("文件绝对路径：" + saveImageName);
					poster.saveAs(saveImageName);
//					System.out.println(currentId+" "+saveImageName);
					//将存在本地的图片路径写入数据库图片表，并与对应的商品表的关联起来
					//System.out.println(file.getName());
					GoodsImages gi = new GoodsImages();
					gi.setGoodId(currentId);
					gi.setUrl((saveImageName.substring(saveImageName.indexOf("goodsimages"))).replace('\\', '/'));
//					System.out.println((saveImageName.substring(saveImageName.indexOf("goodsimages"))).replace('\\', '/'));
					//System.out.println(FileDirectory.getHttpUrl());
					gis.save(gi);
					
					//System.out.println(System.getProperty("user.dir"));
				}
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
