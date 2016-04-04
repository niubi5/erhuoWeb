package com.gem.erhuo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Markets;
import com.gem.erhuo.entity.UserMarket;
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

	public void marketUsersCountPlus(int marketId) {
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

	public void marketUsersCountSub(int marketId) {
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

	// 获得关注该集市的所有用户id
	public List<Integer> getMarketUserId(int marketId) {
		List<Integer> list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql;
		InputStream is = null;
		try {
			list = (List<Integer>) new ArrayList<Integer>();
			sql = "select * from usermarket where marketid = ?";
			prep.setInt(1, marketId);
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				UserMarket um = new UserMarket();
				um.setUserId(rs.getInt("userid"));
				um.setMarketId(rs.getInt("marketid"));
				um.setFocTime(rs.getString("foctime"));
				// 将对象加到集合中
				list.add(um.getUserId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(conn != null)
					conn.close();
					if(prep != null)
						prep.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
}
