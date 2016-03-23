package com.gem.erhuo.dao;

import com.gem.erhuo.entity.GoodsImages;

	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
