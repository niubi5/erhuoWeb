package com.gem.erhuo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Remark;
import com.gem.erhuo.util.DBConnection;

public class RemarkDao extends BaseDaoImpl<Remark> {

	private List<Remark> listRemarks = new ArrayList<Remark>();

	// 通过商品Id 返回他的所有评论
	public List<Remark> getAll(int goodsId) {
		// 找出所有一级评论 即父id为null
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBConnection.getConnection();
			sql = "select * from remark where goods_id = ? and father_id is null order by comment_time";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, goodsId);
//			prep.setInt(2, (curPage - 1) * pageSize);
//			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				remark.setId(rs.getInt("id"));
				remark.setGoodsId(goodsId);
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
				remark.setIsEnd(rs.getInt("is_end"));
				listRemarks.add(remark);
				// 通过一级评论id找出二级评论
				List<Integer> ids = getChildRemark(rs.getInt("id"));
				for (Integer id : ids) {
					getChildRemark(id);
				}
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
					prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listRemarks;
	}

	// 通过父评论找出子评论
	private List<Integer> getChildRemark(int fatherId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where father_id = ? order by comment_time";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, fatherId);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				remark.setId(rs.getInt("id"));
				remark.setGoodsId(rs.getInt("goods_id"));
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
				remark.setIsEnd(rs.getInt("is_end"));
				listRemarks.add(remark);
				ids.add(rs.getInt("id"));
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
		return ids;
	}

	public void saveRemark(Remark remark) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into remark(goods_id,user_id,comment_content,comment_time,father_id,is_end) values(?,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, remark.getGoodsId());
			prep.setInt(2, remark.getUserId());
			prep.setString(3, remark.getComment_content());
			prep.setString(4, remark.getComment_time());
			if(remark.getFatherId() == 0){
				prep.setObject(5, null);
			} else {
				prep.setInt(5, remark.getFatherId());
			}
			prep.setInt(6, remark.getIsEnd());
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
	
	
	// 通过商品Id 返回他的所有评论
		public List<Remark> getAllRemarkByUserId(int userId) {
			Connection conn = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			String sql = null;
			try {
				conn = DBConnection.getConnection();
				sql = "select * from remark where user_id = ?  order by comment_time";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, userId);
//				prep.setInt(2, (curPage - 1) * pageSize);
//				prep.setInt(3, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Remark remark = new Remark();
					remark.setId(rs.getInt("id"));
					remark.setGoodsId(userId);
					remark.setUserId(rs.getInt("goods_id"));
					remark.setComment_content(rs.getString("comment_content"));
					remark.setComment_time(rs.getString("comment_time"));
					remark.setFatherId(rs.getInt("father_id"));
					remark.setIsEnd(rs.getInt("is_end"));
					listRemarks.add(remark);
					// 通过一级评论id找出二级评论
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
						prep.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listRemarks;
		}


}
