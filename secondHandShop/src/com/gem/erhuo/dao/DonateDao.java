package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Donates;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.util.DBConnection;

public class DonateDao extends BaseDaoImpl<Donates> {
	//获得用户的捐赠
	public List<Donates> getUserDonate(int curPage, int pageSize, int userId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Donates> listDonate = new ArrayList<Donates>();
		try {
			conn = DBConnection.getConnection();
			// 按时间排序 并且为上架状态
			String sql = "select * from donates where userid = ? order by dontime desc limit ?,? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Donates d = new Donates();
				d.setId(rs.getInt("id"));
				d.setHelpId(rs.getInt("helpid"));
				d.setUserId(rs.getInt("userid"));
				d.setTitle(rs.getString("title"));
				d.setBrief(rs.getString("brief"));
				d.setDonTime(rs.getString("dontime"));
				d.setLogisticsCom(rs.getString("logisticscom"));
				d.setLogisticsNum(rs.getString("logisticsnum"));
				// 将对象加到集合中
				listDonate.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDonate;
	}
	
	//获得某个求助信息的所有捐赠
	public List<Donates> getHelpDonate(int helpId){
	Connection conn = null;
	PreparedStatement prep = null;
	ResultSet rs = null;
	List<Donates> listDonate = new ArrayList<Donates>();
	try {
		conn = DBConnection.getConnection();
		// 按时间排序 并且为上架状态
		String sql = "select * from donates where helpid =? order by dontime";
		prep = conn.prepareStatement(sql);
		prep.setInt(1, helpId);
		rs = prep.executeQuery();
		while (rs.next()) {
			Donates d = new Donates();
			d.setId(rs.getInt("id"));
			d.setHelpId(rs.getInt("helpid"));
			d.setUserId(rs.getInt("userid"));
			d.setTitle(rs.getString("title"));
			d.setBrief(rs.getString("brief"));
			d.setDonTime(rs.getString("dontime"));
			d.setLogisticsCom(rs.getString("logisticscom"));
			d.setLogisticsNum(rs.getString("logisticsnum"));
			// 将对象加到集合中
			listDonate.add(d);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (conn != null)
				conn.close();
			if (prep != null)
				prep.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return listDonate;
}
}
