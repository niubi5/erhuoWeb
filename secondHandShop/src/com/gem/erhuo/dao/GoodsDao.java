package com.gem.erhuo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.util.DBConnection;

public class GoodsDao extends BaseDaoImpl<Goods> {

	@Override
	public List<Goods> getAll(Goods t) {
		// TODO Auto-generated method stub
		return super.getAll(t);
	}

	public List<Goods> getPagedGoods(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Goods> listGoods = new ArrayList<Goods>();
		try {
			conn = DBConnection.getConnection();
			// 按时间排序 并且为上架状态
			String sql = "select * from goods where state = 1  order by pubtime desc limit ?,? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setUserId(rs.getInt("userid"));
				goods.setName(rs.getString("name"));
				goods.setImformation(rs.getString("imformation"));
				goods.setTypeId(rs.getInt("typeid"));
				goods.setSoldPrice(rs.getDouble("soldprice"));
				goods.setBuyPrice(rs.getDouble("buyprice"));
				goods.setMarketId(rs.getInt("marketid"));
				goods.setLongitude(rs.getDouble("longitude"));
				goods.setLatitude(rs.getDouble("latitude"));
				goods.setPubTime(rs.getString("pubtime"));
				goods.setState(rs.getInt("state"));
				listGoods.add(goods);
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
		return listGoods;
	}
	//获得在售中的商品
	public List<Goods> getSellingPagedGoods(int curPage, int pageSize,int userId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Goods> listGoods = new ArrayList<Goods>();
		try {
			conn = DBConnection.getConnection();
			// 按时间排序 并且为上架状态
			String sql = "select * from goods where state = 1 and userid = ? order by state desc limit ?,? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setUserId(rs.getInt("userid"));
				goods.setName(rs.getString("name"));
				goods.setImformation(rs.getString("imformation"));
				goods.setTypeId(rs.getInt("typeid"));
				goods.setSoldPrice(rs.getDouble("soldprice"));
				goods.setBuyPrice(rs.getDouble("buyprice"));
				goods.setMarketId(rs.getInt("marketid"));
				goods.setLongitude(rs.getDouble("longitude"));
				goods.setLatitude(rs.getDouble("latitude"));
				goods.setPubTime(rs.getString("pubtime"));
				goods.setState(rs.getInt("state"));
				listGoods.add(goods);
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
		return listGoods;
	}
	
	//获得已卖出的商品
		public List<Goods> getSoldPagedGoods(int curPage, int pageSize,int userId) {
			Connection conn = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			List<Goods> listGoods = new ArrayList<Goods>();
			try {
				conn = DBConnection.getConnection();
				// 按时间排序 并且为上架状态
				String sql = "select * from goods where state > 1 and userid = ? order by state desc limit ?,? ";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, userId);
				prep.setInt(2, (curPage - 1) * pageSize);
				prep.setInt(3, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Goods goods = new Goods();
					goods.setId(rs.getInt("id"));
					goods.setUserId(rs.getInt("userid"));
					goods.setName(rs.getString("name"));
					goods.setImformation(rs.getString("imformation"));
					goods.setTypeId(rs.getInt("typeid"));
					goods.setSoldPrice(rs.getDouble("soldprice"));
					goods.setBuyPrice(rs.getDouble("buyprice"));
					goods.setMarketId(rs.getInt("marketid"));
					goods.setLongitude(rs.getDouble("longitude"));
					goods.setLatitude(rs.getDouble("latitude"));
					goods.setPubTime(rs.getString("pubtime"));
					goods.setState(rs.getInt("state"));
					listGoods.add(goods);
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
			return listGoods;
		}

	
	//搜索商品
	public List<Goods> getGoodsList(String word,int curPage, int pageSize){
		List<Goods> listGoods=new ArrayList<Goods>();
		System.out.println("传入"+word);
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getConnection();
			String sql="select * from goods where name like ? order by state desc limit ?,?";
//			String sql="select * from goods where name like '%"+word+"%' order by pubtime desc limit ?,?";
			prep=conn.prepareStatement(sql);
			prep.setString(1,"%"+word+"%");
			prep.setInt(2, (curPage - 1) * pageSize);
			prep.setInt(3, pageSize);
			rs=prep.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setUserId(rs.getInt("userid"));
				goods.setName(rs.getString("name"));
				goods.setImformation(rs.getString("imformation"));
				goods.setTypeId(rs.getInt("typeid"));
				goods.setSoldPrice(rs.getDouble("soldprice"));
				goods.setBuyPrice(rs.getDouble("buyprice"));
				goods.setMarketId(rs.getInt("marketid"));
				goods.setLongitude(rs.getDouble("longitude"));
				goods.setLatitude(rs.getDouble("latitude"));
				goods.setPubTime(rs.getString("pubtime"));
				goods.setState(rs.getInt("state"));
				listGoods.add(goods);
				System.out.println("结果"+listGoods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		return listGoods;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
