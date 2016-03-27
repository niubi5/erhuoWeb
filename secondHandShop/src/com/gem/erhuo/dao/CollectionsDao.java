package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.gem.erhuo.entity.Collections;
import com.gem.erhuo.util.DBConnection;

/**
 * @author LuoShiHeng
 *
 */

public class CollectionsDao extends BaseDaoImpl<Collections> {

	public void saveCollection(int userId, int goodsId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into collections(userid,goodid,coltime) values(?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, goodsId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(new Date(System.currentTimeMillis()));
			prep.setString(3, str);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void deleteCollection(int userId, int goodsId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from collections where userid = ? and goodid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, goodsId);
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(prep != null)
					prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
