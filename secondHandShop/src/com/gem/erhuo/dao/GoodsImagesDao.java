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

import com.gem.erhuo.util.DBConnection;
import com.gem.erhuo.util.Url;

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
			url = Url.getHeadUrl();// 获得url头部
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
	
	//获得分类的商品集合
	public List<Goods> getClassificaImages(int tag,int curPage, int pageSize){
		List<Goods> listGoods = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql=null;
		try {
			conn = DBConnection.getConnection();
			if(tag==1){
				sql="select * from goods where name like '%苹果%' or name like '%iphone%' order by pubtime desc limit ?,?";
			}else if(tag==2){
				sql="select * from goods where name like '%平板电脑%' order by pubtime desc limit ?,?";
			}else if(tag==3){
				sql="select * from goods where name like '%电脑%' or name like '%平板%' order by pubtime desc limit ?,?";
			}else if(tag==4){
				sql="select * from goods where name like '%小米%' order by pubtime desc limit ?,?";
			}else if(tag==5){
				sql="select * from goods where name like '%数码%' order by pubtime desc limit ?,?";
			}else if(tag==6){
				sql="select * from goods where name like '%书%' order by pubtime desc limit ?,?";
			}else if(tag==7){
				sql="select * from goods where name like '%衣%' or name like '%鞋%'or name like '%包%' order by pubtime desc limit ?,?";
			}else if(tag==8){
				sql="select * from goods where name like '%化妆%' or name like '%霜%' order by pubtime desc limit ?,?";
			}
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setUserId(rs.getInt("userid"));
				goods.setName(rs.getString("name"));
				goods.setImformation(rs.getString("imformation"));
				goods.setTypeId(rs.getInt("typeid"));
				goods.setSoldPrice(rs.getDouble("soldprice"));
				goods.setBuyPrice(rs.getDouble("buyprice"));
				goods.setMarketId(rs.getInt("marketid"));
				goods.setLongitude(rs.getDouble("longitude"));
				goods.setLatitude(rs.getDouble("latitude"));
				goods.setPubTime(rs.getString("pubtime"));
				goods.setState(rs.getInt("state"));
				listGoods.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return listGoods;
	}
	
}
