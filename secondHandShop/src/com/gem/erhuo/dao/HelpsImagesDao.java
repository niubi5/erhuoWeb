package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gem.erhuo.entity.HelpsImages;
import com.gem.erhuo.util.DBConnection;
import com.gem.erhuo.util.Url;

public class HelpsImagesDao extends BaseDaoImpl<HelpsImages> {

	// 获得求助图片的集合
	public List<String> getGoodsImagesUrl(int helpsId) {
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
			String sql = "select url from helpsimages where helpid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, helpsId);
			rs = prep.executeQuery();
			while (rs.next()) {
				// 拼接url
				String urlAll = url + "/" + rs.getString("url");
				urls.add(urlAll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return urls;
	}

}
