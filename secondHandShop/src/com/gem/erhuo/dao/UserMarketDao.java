package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gem.erhuo.entity.UserMarket;
import com.gem.erhuo.util.DBConnection;

public class UserMarketDao extends BaseDaoImpl<UserMarket> {

	public void saveUserMarket(int userId, int marketId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into usermarket values(?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, marketId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			prep.setString(3, sdf.format(new Date()));
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

	public void deleteUserMarket(int userId, int marketId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from usermarket where userid = ? and marketid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, marketId);
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

	public List<Integer> getUserMarketIDById(int userId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from usermarket where userid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			rs = prep.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("marketid"));
			}
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
		return list;
	}
}
