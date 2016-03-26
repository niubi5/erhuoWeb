package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.util.DBConnection;

public class AddressDao extends BaseDaoImpl<Address>{
      
	public Address getUserAddressByPhone(String phone){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from address where phone=? ";
			prep=conn.prepareStatement(sql);
			prep.setString(1, phone);
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

	
	
}
