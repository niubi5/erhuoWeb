package com.gem.erhuo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.util.DBConnection;
import com.gem.erhuo.util.Url;

public class UserDao extends BaseDaoImpl<Users> {
	// 根据账号查找用户
	public Users getUserByIdentity(String identity) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		Users u = null;
		InputStream is = null;
		String url = null;
		try {
			url = Url.getHeadUrl();// 获得url头部
			conn = DBConnection.getConnection();
			sql = "select * from users where identity =?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, identity);
			rs = prep.executeQuery();
			while (rs.next()) {
				u = new Users();
				u.setId(rs.getInt("id"));
				u.setIdentity(rs.getString("identity"));
				u.setPwd(rs.getString("pwd"));
				u.setPhoto(url  + "/"+ rs.getString("photo"));
				u.setName(rs.getString("name"));
				u.setSex(rs.getInt("sex"));
				u.setJifen(rs.getInt("jifen"));
				u.setInvCode(rs.getString("invcode"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw new RuntimeException();
			}

		}
		return u;

	}
	//修改密码
		public void update(String phone,String pwd) {
			Connection conn = null;
			PreparedStatement prep = null;
			InputStream is = null;
			
				try {
					conn = DBConnection.getConnection();
					String 	sql = "update users set pwd=? where identity=?";
					prep = conn.prepareStatement(sql);
					prep.setString(1, pwd);
					prep.setString(2, phone);
					prep.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		
		//修改用户信息
		public void updateUser(String name,String phone,String sex){
			Connection conn = null;
			PreparedStatement prep = null;
			try {
				conn=DBConnection.getConnection();
				String sql="update users set name=?,sex=? where identity=?";
				prep=conn.prepareStatement(sql);
				prep.setString(1, name);
				prep.setInt(2,Integer.parseInt(sex));
				prep.setString(3, phone);
				prep.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
//		public Users getByGoodsId(int goodsId) {
//			Users user = new Users();
//			Connection conn = null;
//			PreparedStatement prep = null;
//			ResultSet rs = null;
//			try {
//				conn = DBConnection.getConnection();
//				String sql = "select * from users where id = ?";
//				prep = conn.prepareStatement(sql);
//				prep.setInt(1, goodsId);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
}
