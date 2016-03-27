package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.util.DBConnection;

public class AddressDao extends BaseDaoImpl<Address>{
      
	public Address getUserAddressByUserId(String userid){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from address where userid=? and isdefault='yes'";
			prep=conn.prepareStatement(sql);
			prep.setString(1, userid);
			rs=prep.executeQuery();
			while(rs.next()){
				Address address=new Address();
				address.setId(rs.getInt("id"));
				address.setName(rs.getString("name"));
				address.setUserId(rs.getInt("userid"));
				address.setPhone(rs.getString("phone"));
				address.setAddress(rs.getString("address"));
				return address;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	//保存地址
	public void saveAddress(Address address){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="insert into address(userId,name,phone,address,isdefault)"
					+ "values(?,?,?,?,?)";
			prep=conn.prepareStatement(sql);
			prep.setInt(1, address.getUserId());
			prep.setString(2,address.getName());
			prep.setString(3, address.getPhone());
			prep.setString(4, address.getAddress());
			prep.setString(5, address.getIsdefault());
			prep.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateAddressIsdefault(String userid){
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn=DBConnection.getConnection();
			String sql="update address set isdefault='no' where userid=?";
			prep=conn.prepareStatement(sql);
			prep.setInt(1, Integer.parseInt(userid));
			prep.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
