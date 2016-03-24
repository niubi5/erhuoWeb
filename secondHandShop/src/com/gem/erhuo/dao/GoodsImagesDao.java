package com.gem.erhuo.dao;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.entity.Users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.util.DBConnection;

public class GoodsImagesDao extends BaseDaoImpl<GoodsImages> {
	
	// 获得商品图片的集合
	public List<String> getGoodsImagesUrl(int goodsId){
		List<String> urls = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Properties prop = null;
		String url = null;
		try {
			prop = new Properties();
			prop.load(GoodsImagesDao.class.getResourceAsStream("/com/gem/erhuo/util/url.properties"));
			url = prop.getProperty("url");// 获得url
			conn = DBConnection.getConnection();
			String sql = "select url from goodsimages where goodid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, goodsId);
			rs = prep.executeQuery();
			while(rs.next()){
				// 拼接url
				String urlAll = url + "/" +  rs.getString("url");
				urls.add(urlAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prep != null)prep.close();
				if(conn != null)conn.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return urls;
	}
	
	//获得分类的图片集合
	public List<Goods> getClassificaImagesUrl(int tag){
		List<Goods> urls = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Properties prop = null;
		String sql=null;
		try {
			prop = new Properties();
			conn = DBConnection.getConnection();
			if(tag==1){
				sql="select * from goods where name like '%苹果%' or name like '%iphone%'";
			}else if(tag==2){
				sql="select * from goods where name like '%平板电脑%' ";
			}else if(tag==3){
				sql="select * from goods where name like '%电脑%' and name like '%/!平板%'";
			}else if(tag==4){
				sql="select * from goods where name like '%小米%' ";
			}else if(tag==5){
				sql="select * from goods where name like '%数码%' ";
			}else if(tag==6){
				sql="select * from goods where name like '%书%' ";
			}else if(tag==7){
				sql="select * from goods where name like '%衣%' or name like '%鞋%'or name like '%包%'";
			}else if(tag==8){
				sql="select * from goods where name like '%化妆%' or name like '%霜%'";
			}
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
//			while (rs.next()) {
//				Goods goods = new Goods();
//				goods.setId(rs.getInt("id"));
//				goods.setUserId(rs.getInt("userid"));
//				goods.setName(rs.getString("name"));
//				goods.setImformation(rs.getString("imformation"));
//				goods.setTypeId(rs.getInt("typeid"));
//				goods.setSoldPrice(rs.getDouble("soldprice"));
//				goods.setBuyPrice(rs.getDouble("buyprice"));
//				goods.setMarketId(rs.getInt("marketid"));
//				goods.setLongitude(rs.getDouble("longitude"));
//				goods.setLatitude(rs.getDouble("latitude"));
//				goods.setPubTime(rs.getDate("pubtime"));
//				goods.setState(rs.getInt("state"));
//				listGoods.add(goods);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		return null;
	     
	}
	
}
