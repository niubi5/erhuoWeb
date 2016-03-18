package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.util.DBConnection;

public class GoodsImagesDao extends BaseDaoImpl<GoodsImages> {

	// 获得商品图片的集合
	public List<String> getGoodsImagesUrl(int goodsId){
		List<String> urls = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select url from goodsimages where goodid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, goodsId);
			rs = prep.executeQuery();
			while(rs.next()){
				String url = rs.getString("url");
				urls.add(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(prep != null)prep.close();
				if(conn != null)prep.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return urls;
	}
}
