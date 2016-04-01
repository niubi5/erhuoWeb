package com.gem.erhuo.dao;

import java.io.IOException;
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
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				remark.setId(rs.getInt("id"));
				remark.setGoodsId(goodsId);
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
				// 装入集合中
				listRemarks.add(remark);
				// 通过一级评论id找出二级评论
				List<Integer> ids = getChildRemark(rs.getInt("id"));
				// 递归调用
				for (Integer id : ids) {
					getChildRemark(id);// 这个方法通过父评论Id查找出子评论，并且将子评论装入了Remark集合
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
				// 将子评论装入集合，实现排序
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
			String sql = "insert into remark(goods_id,user_id,comment_content,"
					+ "comment_time,father_id) values(?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, remark.getGoodsId());
			prep.setInt(2, remark.getUserId());
			prep.setString(3, remark.getComment_content());
			prep.setString(4, remark.getComment_time());
			if (remark.getFatherId() == 0) {
				prep.setObject(5, null);
			} else {
				prep.setInt(5, remark.getFatherId());
			}
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
			sql = "select * from remark where user_id = ?  order by comment_time desc";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			// prep.setInt(2, (curPage - 1) * pageSize);
			// prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				remark.setId(rs.getInt("id"));
				remark.setGoodsId(rs.getInt("goods_id"));
				remark.setUserId(userId);
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
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

	// 通过father_id找到remark
	public List<Remark> getAllByFatherID(int fatherId, int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Remark> list = new ArrayList<Remark>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where father_id = ? limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, fatherId);
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				remark.setId(rs.getInt("id"));
				remark.setUserId(rs.getInt("goods_id"));
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(fatherId);
				list.add(remark);
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
		return list;
	}

	public List<Remark> getMyReceiveRemarks(int userId, int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Remark> list = new ArrayList<Remark>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where user_id = ? and father_id is not null limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark r = new Remark();
				r.setId(rs.getInt("id"));
				r.setGoodsId(rs.getInt("goods_id"));
				r.setUserId(rs.getInt("user_id"));
				r.setComment_content(rs.getString("comment_content"));
				r.setComment_time(rs.getString("comment_time"));
				r.setFatherId(rs.getInt("father_id"));
				list.add(r);
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
		return list;
	}

	// public Remark getById(int remarkId) {
	// Connection conn = null;
	// PreparedStatement prep = null;
	// ResultSet rs = null;
	// Remark remark = null;
	// try {
	// conn = DBConnection.getConnection();
	// String sql = "select * from remark where remark_id = ?";
	// prep = conn.prepareStatement(sql);
	// prep.setInt(1, remarkId);
	// rs = prep.executeQuery();
	// while(rs.next()){
	// remark.setId(rs.getInt("id"));
	// remark.setUserId(rs.getU);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

}
