package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gem.erhuo.entity.Markets;
import com.gem.erhuo.util.DBConnection;

public class MarketsDao extends BaseDaoImpl<Markets> {

	public void marketGoodsCountPlus(int marketId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update markets set goodsCount = goodsCount + 1 where id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, marketId);
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
	
	public void marketUsersCountPlus(int marketId){
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update markets set userCount = userCount + 1 where id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, marketId);
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
	
	public void marketUsersCountSub(int marketId){
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update markets set userCount = userCount - 1 where id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, marketId);
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
}
