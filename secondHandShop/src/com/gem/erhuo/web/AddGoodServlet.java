package com.gem.erhuo.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.GoodsImagesService;
import com.gem.erhuo.service.GoodService;
import com.gem.erhuo.service.UserService;
import com.gem.erhuo.util.FileDirectory;
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
//		String userId = request.getParameter("userId");
//		String name = request.getParameter("name");
//		String imformation = request.getParameter("imformation");
//		String typeId = request.getParameter("typeId");
//		String soldPrice = request.getParameter("soldPrice");
//		String buyPrice = request.getParameter("buyPrice");
//		String marketId = request.getParameter("marketId");
//		String longitude = request.getParameter("longitude");
//		String latitude = request.getParameter("latitude");
//		String pubTime = request.getParameter("pubTime");
//		String state = request.getParameter("state");
//		Goods good = new Goods();
//		good.setUserId(Integer.parseInt(userId));
//		good.setName(name);
//		good.setImformation(imformation);
//		good.setTypeId(Integer.parseInt(typeId));
//		good.setSoldPrice(Double.parseDouble(soldPrice));
//		good.setBuyPrice(Double.parseDouble(buyPrice));
//		good.setMarketId(Integer.parseInt(marketId));
//		good.setLongitude(Double.parseDouble(longitude));
//		good.setLatitude(Double.parseDouble(latitude));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		try {
//			good.setPubTime(sdf.parse(pubTime));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		good.setState(Integer.parseInt(state));
		/**###################################*/
//		String goodJson = request.getParameter("goodJson");
//		System.out.println(goodJson);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
//		Goods good = gson.fromJson(goodJson,Goods.class);
//		GoodService gs = new GoodService();
//		int currentId = gs.save(good);
//		System.out.println(currentId);
//		PrintWriter pw = response.getWriter();
//		pw.print(currentId);
//		pw.flush();
//		pw.close();
		
		
		//使用SmartUpload来处理上传的图片
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		try {
			smartUpload.upload();
			//获取商品信息
			String goodJson = smartUpload.getRequest().getParameter("goodJson");
			//处理获得的商品的文字信息
			System.out.println(goodJson);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
			Goods good = gson.fromJson(goodJson,Goods.class);
			GoodService gs = new GoodService();
			GoodsImagesService gis = new GoodsImagesService();
			//保存商品文字信息，返回数据库自增长id
			int currentId = gs.save(good);
			System.out.println(currentId);
			//处理获得的图片信息
			String realpath = this.getServletContext().getRealPath("GoodsImages");
			//String realpath = FileDirectory.getFileSaveDirectory()+"\\GoodsImages";
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
					//客户端传过来的图片名
					String imageName = poster.getFileName();
					File file = new File(getServletContext().getRealPath("GoodsImages"),""+currentId+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					
					//File file = new File(imageDir,""+currentId+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."), imageName.length()));
					//文件的保存路径
					String saveImageName = file.getAbsolutePath();
					poster.saveAs(saveImageName);
					System.out.println(currentId+" "+saveImageName);
					//将存在本地的图片路径写入数据库图片表，并与对应的商品表的关联起来
					GoodsImages gi = new GoodsImages();
					gi.setGoodId(currentId);
					gi.setUrl((FileDirectory.getHttpUrl()+saveImageName.substring(saveImageName.lastIndexOf("GoodsImages"), saveImageName.length())).replace('\\', '/'));
					System.out.println(gis.save(gi));
					
					//System.out.println(System.getProperty("user.dir"));
				}
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
