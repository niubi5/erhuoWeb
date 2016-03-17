package com.gem.erhuo.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gem.erhuo.entity.Users;
import com.gem.erhuo.util.DBConnection;

public class UserDao extends BaseDaoImpl<Users> {
	// 根据账号查找用户
	public Users getUserByIdentity(String identity) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		Users u = null;
		InputStream is = null;
		try {
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
				u.setPhoto(rs.getString("photo"));
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
}
