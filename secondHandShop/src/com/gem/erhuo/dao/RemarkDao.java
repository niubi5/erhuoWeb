package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gem.erhuo.entity.Remark;
import com.gem.erhuo.util.DBConnection;

public class RemarkDao extends BaseDaoImpl<Remark> {

	private List<Remark> listRemarks;

	// 通过商品Id 返回他的所有评论
	public List<Remark> getAll(int goodsId) {
		// 找出所有一级评论 即父id为null
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		listRemarks = new ArrayList<Remark>();
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
				getChildRemark(rs.getInt("id"));
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
		// 排序 时间从小到大
		Collections.sort(listRemarks, new Comparator<Remark>() {

			@Override
			public int compare(Remark o1, Remark o2) {
				String time1 = o1.getComment_time();
				String time2 = o2.getComment_time();
				if(time1.compareTo(time2) > 0){
					return 1;
				} else if(time1.compareTo(time2) < 0) {
					return -1;
				} else 
				return 0;
			}
		});
		return listRemarks;
	}

	// 通过父评论找出子评论
	public int getChildRemark(int fatherId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int id = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where father_id = ? order by comment_time";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, fatherId);
			rs = prep.executeQuery();
			while (rs.next()) {
				Remark remark = new Remark();
				id = rs.getInt("id");
				remark.setId(id);
				remark.setGoodsId(rs.getInt("goods_id"));
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
				remark.setIsEnd(rs.getInt("is_end"));
				listRemarks.add(remark);
				if (remark.getIsEnd() == 0) {
					continue;// 当前为终极评论了  跳到下次循环
				} else {
					// 否则继续查找子评论
					if(getChildRemark(rs.getInt("id")) == 0){
						break;// 没有查到 结束循环
					}
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
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
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

	public void update(int id) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update remark set is_end = 1 where id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
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
		List<Remark> list = new ArrayList<>();
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
				list.add(remark);
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
		return list;
	}

	// 通过father_id找到remark
	public List<Remark> getAllByFatherID(int fatherId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Remark> list = new ArrayList<Remark>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where father_id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, fatherId);
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
			String sql = "select * from remark where father_id in (select id from remark where user_id = ?) order by comment_time desc limit ?,?";
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

	public Remark getRemarkById(int remarkId) {
		Remark remark = new Remark();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from remark where id = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, remarkId);
			rs = prep.executeQuery();
			while (rs.next()) {
				remark.setId(rs.getInt("id"));
				remark.setGoodsId(rs.getInt("goods_id"));
				remark.setUserId(rs.getInt("user_id"));
				remark.setComment_content(rs.getString("comment_content"));
				remark.setComment_time(rs.getString("comment_time"));
				remark.setFatherId(rs.getInt("father_id"));
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
		return remark;
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
