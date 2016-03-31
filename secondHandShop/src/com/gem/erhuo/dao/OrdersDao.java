package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.util.DBConnection;

public class OrdersDao extends BaseDaoImpl {
	public List<Orders> getUserOrders(int curPage, int pageSize,int userId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Orders> listOrders = new ArrayList<Orders>();
		try {
			conn = DBConnection.getConnection();
			// 按时间排序 并且为上架状态
			String sql = "select * from orders where userid = ? order by status desc limit ?,? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Orders o = new Orders();
				o.setId(rs.getInt("id"));
				o.setGoodId(rs.getInt("goodid"));
				o.setUserId(rs.getInt("userid"));
				o.setOrderNum(rs.getString("ordernum"));
				o.setCreateTime(rs.getString("createtime"));
				o.setPayTime(rs.getString("paytime"));
				o.setSendTime(rs.getString("sendtime"));
				o.setCompleteTime(rs.getString("completetime"));
				o.setState(rs.getInt("state"));
				o.setLogisticsCom(rs.getString("logisticscom"));
				o.setLogisticsNum(rs.getString("logisticsnum"));
				// 将对象加到集合中
				listOrders.add(o);
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
		return listOrders;
	}
}
