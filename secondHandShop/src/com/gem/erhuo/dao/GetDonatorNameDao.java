package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.util.DBConnection;

/**
 * 查询对同一个Help捐赠过的人的Name
 * @author Administrator
 *
 */
public class GetDonatorNameDao {

	/**
	 * 获得对同一个Help捐赠过的人的Name
	 * @return
	 */
	public List<String> getUserName(int helpId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Integer> userIds;
		List<String> userNames;
		String sql;
		try {
			conn = DBConnection.getConnection();
			sql = "select name from users where id = ?";
			prep = conn.prepareStatement(sql);
			userIds = getUserId(helpId);
			userNames = new ArrayList<String>();
			for(int i = 0; i < userIds.size();i++){
				prep.setInt(1, userIds.get(i));
				rs = prep.executeQuery();
				while(rs.next()){
					userNames.add(rs.getString("name"));
				}
			}
			return userNames;
		} catch (Exception e) {

		}finally{
			try {
				if(conn!=null)conn.close();
				if(prep!=null)prep.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	

	/**
	 * 获得对同一个Help捐赠过的人的id
	 * @param helpId
	 * @return
	 */
	public List<Integer> getUserId(int helpId){
		Connection conn = null  ;
		String sql ;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Integer> userIds;
		try {
			conn = DBConnection.getConnection();
			sql = "select userid from donates where helpid = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, helpId);
			rs = prep.executeQuery();
			userIds = new ArrayList<Integer>();
			while(rs.next()){
				userIds.add(rs.getInt("userid"));
			}
			return userIds;
		} catch (Exception e) {
		}finally{
			try {
				if(conn!=null) conn.close();
				if(prep!=null)prep.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
}
