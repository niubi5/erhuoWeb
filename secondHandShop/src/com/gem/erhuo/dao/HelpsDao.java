package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.util.DBConnection;

public class HelpsDao extends BaseDaoImpl<Helps>{

	
	// 保存求助信息
	@Override
	public int save(Helps t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}

	// 获得所有求助信息
	@Override
	public List<Helps> getAll(Helps t) {
		return super.getAll(t);
	}
	
	// 获得分页求助信息
	public List<Helps> getPagedHelps(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Helps> listhelps = new ArrayList<Helps>();
		try {
			conn = DBConnection.getConnection();
			// 按时间排序
			String sql = "select * from helps order by pubtime desc limit ?,? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Helps helps = new Helps();
				helps.setId(rs.getInt("id"));
				helps.setUserId(rs.getInt("userid"));
				helps.setTitle(rs.getString("title"));
				helps.setDetail(rs.getString("detail"));
				helps.setPubTime(rs.getString("pubtime"));
				helps.setLogistics(rs.getString("logistics"));
				helps.setConsignee(rs.getString("consignee"));
				helps.setAddress(rs.getString("address"));
				listhelps.add(helps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return listhelps;
	}
	
	// 获得分页求助信息
		public List<Helps> getUserPagedHelps(int curPage, int pageSize,int userId) {
			Connection conn = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			List<Helps> listhelps = new ArrayList<Helps>();
			try {
				conn = DBConnection.getConnection();
				// 按时间排序
				String sql = "select * from helps where userid = ? order by pubtime desc limit ?,? ";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, userId);
				prep.setInt(2, (curPage - 1) * pageSize);
				prep.setInt(3, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Helps helps = new Helps();
					helps.setId(rs.getInt("id"));
					helps.setUserId(rs.getInt("userid"));
					helps.setTitle(rs.getString("title"));
					helps.setDetail(rs.getString("detail"));
					helps.setPubTime(rs.getString("pubtime"));
					helps.setLogistics(rs.getString("logistics"));
					helps.setConsignee(rs.getString("consignee"));
					helps.setAddress(rs.getString("address"));
					listhelps.add(helps);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
			return listhelps;
		}

}
