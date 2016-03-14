package com.gem.erhuo.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.entity.Collections;
import com.gem.erhuo.entity.Donates;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.GoodsImages;
import com.gem.erhuo.entity.GoodsReports;
import com.gem.erhuo.entity.Helps;
import com.gem.erhuo.entity.HelpsImages;
import com.gem.erhuo.entity.HelpsReports;
import com.gem.erhuo.entity.Markets;
import com.gem.erhuo.entity.Messages;
import com.gem.erhuo.entity.Orders;
import com.gem.erhuo.entity.PGoodsImages;
import com.gem.erhuo.entity.PrizeGoods;
import com.gem.erhuo.entity.Types;
import com.gem.erhuo.entity.UserMarket;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.util.DBConnection;

public class BaseDaoImpl<T> implements BaseDao<T> {
	// 保存
	@Override
	public void save(T t) {
		Connection conn = null;
		PreparedStatement prep = null;
		InputStream is = null;
		try {
			// 1、连接数据库
			conn = DBConnection.getConnection();
			// 2、SQL语句
			String sql = null;
			if (t instanceof Users) {
				sql = "insert into users(identity,pwd,photo,name,sex,jifen,invCode) values(?,?,?,?,?,?,?)";
				// 3、获得PreparedStatement对象
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Users) t).getIdentity());
				prep.setString(2, ((Users) t).getPwd());
				if (((Users) t).getPhoto() != null) {
					is = new ByteArrayInputStream(((Users) t).getPhoto());
				} else {
					prep.setBinaryStream(3, is);
				}
				prep.setString(4, ((Users) t).getName());
				prep.setInt(5, ((Users) t).getSex());
				prep.setInt(6, ((Users) t).getJifen());
				prep.setString(7, ((Users) t).getInvCode());
				prep.executeUpdate();

			} else if (t instanceof Goods) {
				sql = "insert into goods(userid,name,imformation,typeid,soldprice,buyprice,marketid,longitude,latitude,pubtime,state) values(?,?,?,?,?,?,?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Goods) t).getUserId());
				prep.setString(2, ((Goods) t).getName());
				prep.setString(3, ((Goods) t).getImformation());
				prep.setInt(4, ((Goods) t).getTypeId());
				prep.setDouble(5, ((Goods) t).getSoldPrice());
				prep.setDouble(6, ((Goods) t).getBuyPrice());
				prep.setInt(7, ((Goods) t).getMarketId());
				prep.setDouble(8, ((Goods) t).getLongitude());
				prep.setDouble(9, ((Goods) t).getLatitude());
				prep.setDate(10, new java.sql.Date(((Goods) t).getPubTime().getTime()));
				prep.setInt(11, ((Goods) t).getState());
				prep.executeUpdate();
			} else if (t instanceof Types) {
				sql = "insert into types(name,inco) values(?,?)";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Types) t).getName());
				if (((Types) t).getInco() != null) {
					is = new ByteArrayInputStream(((Types) t).getInco());
				} else {
					prep.setBinaryStream(2, is);
				}
				prep.executeUpdate();
			} else if (t instanceof Markets) {
				sql = "insert into markets(name,logo,brief) values(?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Markets) t).getName());
				if (((Markets) t).getLogo() != null) {
					is = new ByteArrayInputStream(((Markets) t).getLogo());
				} else {
					prep.setBinaryStream(2, is);
				}
				prep.setString(3, ((Markets) t).getBrief());
				prep.executeUpdate();
			} else if (t instanceof Orders) {
				sql = "insert into orders(goodid,userid,ordernum,createtime,paytime,sendtime,completetime,state,logisticscom,logisticsnum) values(?,?,?,?,?,?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Orders) t).getGoodId());
				prep.setInt(2, ((Orders) t).getUserId());
				prep.setString(3, ((Orders) t).getOrderNum());
				prep.setDate(4, new java.sql.Date(((Orders) t).getCreateTime().getTime()));
				prep.setDate(5, new java.sql.Date(((Orders) t).getPayTime().getTime()));
				prep.setDate(6, new java.sql.Date(((Orders) t).getSendTime().getTime()));
				prep.setDate(7, new java.sql.Date(((Orders) t).getCompleteTime().getTime()));
				prep.setInt(8, ((Orders) t).getState());
				prep.setString(9, ((Orders) t).getLogisticsCom());
				prep.setString(10, ((Orders) t).getLogisticsCom());
				prep.executeUpdate();
			} else if (t instanceof Helps) {
				sql = "insert into helps(userid,title,detail,pubtime,state) values(?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Helps) t).getUserId());
				prep.setString(2, ((Helps) t).getTitle());
				prep.setString(3, ((Helps) t).getDetail());
				prep.setDate(4, new java.sql.Date(((Helps) t).getPubTime().getTime()));
				prep.setInt(5, ((Helps) t).getState());
				prep.executeUpdate();
			} else if (t instanceof Donates) {
				sql = "insert into donates(helpid,userid,dontime,logisticscom,logisticsnum) values(?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Donates) t).getHelpId());
				prep.setInt(2, ((Donates) t).getUserId());
				prep.setDate(3, new java.sql.Date(((Donates) t).getDonTime().getTime()));
				prep.setString(4, ((Donates) t).getLogisticsCom());
				prep.setString(5, ((Donates) t).getLogisticsNum());
				prep.executeUpdate();
			} else if (t instanceof GoodsReports) {
				sql = "insert into goodsreports(goodid,brief,reptime,state) values(?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((GoodsReports) t).getGoodId());
				prep.setString(2, ((GoodsReports) t).getBrief());
				prep.setDate(3, new java.sql.Date(((GoodsReports) t).getRepTime().getTime()));
				prep.setInt(4, ((GoodsReports) t).getState());
				prep.executeUpdate();
			} else if (t instanceof HelpsReports) {
				sql = "insert into helpsreports(helpid,brief,reptime,state) values(?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((HelpsReports) t).getHelpId());
				prep.setString(2, ((HelpsReports) t).getBrief());
				prep.setDate(3, new java.sql.Date(((HelpsReports) t).getRepTime().getTime()));
				prep.setInt(4, ((HelpsReports) t).getState());
				prep.executeUpdate();
			} else if (t instanceof Messages) {
				sql = "insert into messages(goodid,sendid,receiveid,words,sendtime) values(?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Messages) t).getGoodId());
				prep.setInt(2, ((Messages) t).getSendId());
				prep.setInt(3, ((Messages) t).getReceiveId());
				prep.setString(4, ((Messages) t).getWords());
				prep.setDate(5, new java.sql.Date(((Messages) t).getSendTime().getTime()));
				prep.executeUpdate();
			} else if (t instanceof PrizeGoods) {
				sql = "insert into prizegoods(name,detail,price,count) values(?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((PrizeGoods) t).getName());
				prep.setString(2, ((PrizeGoods) t).getDetail());
				prep.setDouble(3, ((PrizeGoods) t).getPrice());
				prep.setInt(4, ((PrizeGoods) t).getCount());
				prep.executeUpdate();
			} else if (t instanceof GoodsImages) {
				sql = "insert into goodsimages(goodid,url) values(?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((GoodsImages) t).getGoodId());
				prep.setString(2, ((GoodsImages) t).getUrl());
				prep.executeUpdate();
			} else if (t instanceof HelpsImages) {
				sql = "insert into helpsimages(helpid,url) values(?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((HelpsImages) t).getHelpId());
				prep.setString(2, ((HelpsImages) t).getUrl());
				prep.executeUpdate();
			} else if (t instanceof PGoodsImages) {
				sql = "insert into pgoodsimages(pgoodid,url) values(?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((PGoodsImages) t).getpGoodId());
				prep.setString(2, ((PGoodsImages) t).getUrl());
				prep.executeUpdate();
			} else if (t instanceof Address) {
				sql = "insert into address(userid,name,phone,address) values(?,?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Address) t).getUserId());
				prep.setString(2, ((Address) t).getName());
				prep.setString(3, ((Address) t).getPhone());
				prep.setString(4, ((Address) t).getAddress());
				prep.executeUpdate();
			} else if (t instanceof Collections) {
				sql = "insert into collections(userid,goodid,clotime) values(?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Collections) t).getUserId());
				prep.setInt(2, ((Collections) t).getGoodId());
				prep.setDate(3, new java.sql.Date(((Collections) t).getColTime().getTime()));
				prep.executeUpdate();
			} else if (t instanceof UserMarket) {
				sql = "insert into usermarket(userid,marketid,foctime) values(?,?,?)";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((UserMarket) t).getUserId());
				prep.setInt(2, ((UserMarket) t).getMarketId());
				prep.setDate(3, new java.sql.Date(((UserMarket) t).getFocTime().getTime()));
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	// 删除
	@Override
	public void delete(int[] ids, T t) {
		Connection conn = null;
		PreparedStatement prep = null;
		String sql = null;
		try {
			conn = DBConnection.getConnection();
			if (t instanceof Users) {
				sql = "delete from users where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Goods) {
				sql = "delete from goods where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Types) {
				sql = "delete from types where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Markets) {
				sql = "delete from markets where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Orders) {
				sql = "delete from orders where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Helps) {
				sql = "delete from helps where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Donates) {
				sql = "delete from donates where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof GoodsReports) {
				sql = "delete from goodsreports where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof HelpsReports) {
				sql = "delete from helpsreports where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Messages) {
				sql = "delete from messages where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof PrizeGoods) {
				sql = "delete from prizeGoods where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof GoodsImages) {
				sql = "delete from goodsImages where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof HelpsImages) {
				sql = "delete from helpsImages where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof PGoodsImages) {
				sql = "delete from pgoodsimages where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Address) {
				sql = "delete from address where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof Collections) {
				sql = "delete from collections where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			} else if (t instanceof UserMarket) {
				sql = "delete from usermarket where id=?";
				prep = conn.prepareStatement(sql);
				for (int id : ids) {
					prep.setInt(1, id);
					prep.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	// 修改
	@Override
	public void update(T t) {
		Connection conn = null;
		PreparedStatement prep = null;
		InputStream is = null;
		try {
			conn = DBConnection.getConnection();
			String sql = null;
			if (t instanceof Users) {
				sql = "update users set identity=?,pwd=?,photo=?,name=?,sex=?,jifen=?,invcode=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Users) t).getIdentity());
				prep.setString(2, ((Users) t).getPwd());
				if (((Users) t).getPhoto() != null) {
					is = new ByteArrayInputStream(((Users) t).getPhoto());
				} else {
					prep.setBinaryStream(3, is);
				}
				prep.setString(4, ((Users) t).getName());
				prep.setInt(5, ((Users) t).getSex());
				prep.setInt(6, ((Users) t).getJifen());
				prep.setString(7, ((Users) t).getInvCode());
				prep.setInt(8, ((Users) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Goods) {
				sql = "update goods set userid=?,name=?,imformation=?,typeid=?,soldprice=?,buyprice=?,marketid=?,longitude=?,latitude=?,pubtime=?,state=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Goods) t).getUserId());
				prep.setString(2, ((Goods) t).getName());
				prep.setString(3, ((Goods) t).getImformation());
				prep.setInt(4, ((Goods) t).getTypeId());
				prep.setDouble(5, ((Goods) t).getSoldPrice());
				prep.setDouble(6, ((Goods) t).getBuyPrice());
				prep.setInt(7, ((Goods) t).getMarketId());
				prep.setDouble(8, ((Goods) t).getLongitude());
				prep.setDouble(9, ((Goods) t).getLatitude());
				prep.setDate(10, new java.sql.Date(((Goods) t).getPubTime().getTime()));
				prep.setInt(11, ((Goods) t).getState());
				prep.setInt(12, ((Goods) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Types) {
				sql = "update types set name=?,inco=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Types) t).getName());
				if (((Types) t).getInco() != null) {
					is = new ByteArrayInputStream(((Types) t).getInco());
				} else {
					prep.setBinaryStream(2, is);
				}
				prep.setInt(3, ((Types) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Markets) {
				sql = "update markets set name=?,logo=?,brief=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((Markets) t).getName());
				if (((Markets) t).getLogo() != null) {
					is = new ByteArrayInputStream(((Markets) t).getLogo());
				} else {
					prep.setBinaryStream(2, is);
				}
				prep.setString(3, ((Markets) t).getBrief());
				prep.setInt(4, ((Markets) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Orders) {
				sql = "update orders set goodid=?,userid=?,ordernumber=?,createtime=?,paytime=?,sendtime=?,completetime=?,state=?,logisticscom=?,logisticsnum=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Orders) t).getGoodId());
				prep.setInt(2, ((Orders) t).getUserId());
				prep.setString(3, ((Orders) t).getOrderNum());
				prep.setDate(4, new java.sql.Date(((Orders) t).getCreateTime().getTime()));
				prep.setDate(5, new java.sql.Date(((Orders) t).getPayTime().getTime()));
				prep.setDate(6, new java.sql.Date(((Orders) t).getSendTime().getTime()));
				prep.setDate(7, new java.sql.Date(((Orders) t).getCompleteTime().getTime()));
				prep.setInt(8, ((Orders) t).getState());
				prep.setString(9, ((Orders) t).getLogisticsCom());
				prep.setString(10, ((Orders) t).getLogisticsNum());
				prep.setInt(11, ((Orders) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Helps) {
				sql = "update helps set userid=?,title=?,detail=?,pubtime=?,state=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Helps) t).getUserId());
				prep.setString(2, ((Helps) t).getTitle());
				prep.setString(3, ((Helps) t).getDetail());
				prep.setDate(4, new java.sql.Date(((Helps) t).getPubTime().getTime()));
				prep.setInt(5, ((Helps) t).getState());
				prep.setInt(6, ((Helps) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Donates) {
				sql = "update donates set helpid=?,userid=?,dontime=?,logisticscom=?,logisticsnum=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Donates) t).getHelpId());
				prep.setInt(2, ((Donates) t).getUserId());
				prep.setDate(3, new java.sql.Date(((Donates) t).getDonTime().getTime()));
				prep.setString(4, ((Donates) t).getLogisticsCom());
				prep.setString(5, ((Donates) t).getLogisticsNum());
				prep.setInt(6, ((Donates) t).getId());
				prep.executeUpdate();
			} else if (t instanceof GoodsReports) {
				sql = "update goodsreports set goodid=?,brief=?,reptime=?,state=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((GoodsReports) t).getGoodId());
				prep.setString(2, ((GoodsReports) t).getBrief());
				prep.setDate(3, new java.sql.Date(((GoodsReports) t).getRepTime().getTime()));
				prep.setInt(4, ((GoodsReports) t).getState());
				prep.setInt(5, ((GoodsReports) t).getId());
				prep.executeUpdate();
			} else if (t instanceof HelpsReports) {
				sql = "update helpsreports set helpid=?,brief=?,reptime=?,state=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((HelpsReports) t).getHelpId());
				prep.setString(2, ((HelpsReports) t).getBrief());
				prep.setDate(3, new java.sql.Date(((HelpsReports) t).getRepTime().getTime()));
				prep.setInt(4, ((HelpsReports) t).getState());
				prep.setInt(5, ((HelpsReports) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Messages) {
				sql = "update messages set goodid=?,sendid=?,receiveid=?,words=?,sendtime=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Messages) t).getGoodId());
				prep.setInt(2, ((Messages) t).getSendId());
				prep.setInt(3, ((Messages) t).getReceiveId());
				prep.setString(4, ((Messages) t).getWords());
				prep.setDate(5, new java.sql.Date(((Messages) t).getSendTime().getTime()));
				prep.setInt(6, ((Messages) t).getId());
				prep.executeUpdate();
			} else if (t instanceof PrizeGoods) {
				sql = "update prizegoods set name=?,detail=?,price=?,count=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setString(1, ((PrizeGoods) t).getName());
				prep.setString(2, ((PrizeGoods) t).getDetail());
				prep.setDouble(3, ((PrizeGoods) t).getPrice());
				prep.setInt(4, ((PrizeGoods) t).getCount());
				prep.setInt(5, ((PrizeGoods) t).getId());
				prep.executeUpdate();
			} else if (t instanceof GoodsImages) {
				sql = "update goodsimages set goodid=?,url=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((GoodsImages) t).getGoodId());
				prep.setString(2, ((GoodsImages) t).getUrl());
				prep.setInt(3, ((GoodsImages) t).getId());
				prep.executeUpdate();
			} else if (t instanceof HelpsImages) {
				sql = "update helpsimages set helpid=?,url=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((HelpsImages) t).getHelpId());
				prep.setString(2, ((HelpsImages) t).getUrl());
				prep.setInt(3, ((HelpsImages) t).getId());
				prep.executeUpdate();
			} else if (t instanceof PGoodsImages) {
				sql = "update pgoodsimages set pgoodid=?,url=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((PGoodsImages) t).getpGoodId());
				prep.setString(2, ((PGoodsImages) t).getUrl());
				prep.setInt(3, ((PGoodsImages) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Address) {
				sql = "update address set userid=?,name=?,phone=?,address=? where id=?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, ((Address) t).getUserId());
				prep.setString(2, ((Address) t).getName());
				prep.setString(3, ((Address) t).getPhone());
				prep.setString(4, ((Address) t).getAddress());
				prep.setInt(5, ((Address) t).getId());
				prep.executeUpdate();
			} else if (t instanceof Collections) {
				// sql = "update collections set userid=?,goodid=?,coltime=?
				// where id=?";
				// prep = conn.prepareStatement(sql);
				// prep.setInt(1, ((Collections) t).getUserId());
				// prep.setInt(2, ((Collections) t).getGoodId());
				// prep.setDate(3, new java.sql.Date(((Collections)
				// t).getColTime().getTime()));
				// prep.executeUpdate();
			} else if (t instanceof UserMarket) {
				// sql = "update usermarket set userid=?,marketid=?,foctime=?
				// where id=?";
				// prep = conn.prepareStatement(sql);
				// prep.setInt(1, ((UserMarket) t).getUserId());
				// prep.setInt(2, ((UserMarket) t).getMarketId());
				// prep.setDate(3, new java.sql.Date(((UserMarket)
				// t).getFocTime().getTime()));
				// prep.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<T> getAll(T t) {
		List<T> list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs;
		String sql;
		InputStream is = null;
		try {
			conn = DBConnection.getConnection();
			if (t instanceof Users) {
				list = (List<T>) new ArrayList<Users>();
				sql = "select * from users";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Users u = new Users();
					u.setId(rs.getInt("id"));
					u.setIdentity(rs.getString("identity"));
					u.setPwd(rs.getString("pwd"));
					if (rs.getBlob("photo") != null) {
						Blob photo_blob = rs.getBlob("photo");
						is = photo_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] bphoto = swapStream.toByteArray();
						u.setPhoto(bphoto);
					} else {
						u.setPhoto(null);
					}
					u.setName(rs.getString("name"));
					u.setSex(rs.getInt("sex"));
					u.setJifen(rs.getInt("jifen"));
					u.setInvCode(rs.getString("invcode"));
					// 将对象加到集合中
					list.add((T) u);
				}
			} else if (t instanceof Goods) {
				list = (List<T>) new ArrayList<Goods>();
				sql = "select * from goods";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Goods g = new Goods();
					g.setId(rs.getInt("id"));
					g.setUserId(rs.getInt("userid"));
					g.setName(rs.getString("name"));
					g.setImformation(rs.getString("imformation"));
					g.setTypeId(rs.getInt("typeid"));
					g.setSoldPrice(rs.getDouble("soldprice"));
					g.setBuyPrice(rs.getDouble("buyprice"));
					g.setMarketId(rs.getInt("marketid"));
					g.setLongitude(rs.getDouble("longitude"));
					g.setLatitude(rs.getDouble("latitude"));
					g.setPubTime(rs.getDate("pubtime"));
					g.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) g);
				}
			} else if (t instanceof Types) {
				list = (List<T>) new ArrayList<Types>();
				sql = "select * from types";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Types types = new Types();
					types.setId(rs.getInt("id"));
					types.setName(rs.getString("name"));
					if (rs.getBlob("inco") != null) {
						Blob inco_blob = rs.getBlob("inco");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] binco = swapStream.toByteArray();
						types.setInco(binco);
					} else {
						types.setInco(null);
					}
					// 将对象加到集合中
					list.add((T) types);
				}
			} else if (t instanceof Markets) {
				list = (List<T>) new ArrayList<Markets>();
				sql = "select * from markets";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Markets m = new Markets();
					m.setId(rs.getInt("id"));
					m.setName(rs.getString("name"));
					if (rs.getBlob("logo") != null) {
						Blob inco_blob = rs.getBlob("logo");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] logo = swapStream.toByteArray();
						m.setLogo(logo);
					} else {
						m.setLogo(null);
					}
					// 将对象加到集合中
					list.add((T) m);
				}
			} else if (t instanceof Orders) {
				list = (List<T>) new ArrayList<Orders>();
				sql = "select * from orders";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Orders o = new Orders();
					o.setId(rs.getInt("id"));
					o.setGoodId(rs.getInt("goodid"));
					o.setUserId(rs.getInt("userid"));
					o.setOrderNum(rs.getString("ordernum"));
					o.setCreateTime(rs.getDate("createtime"));
					o.setPayTime(rs.getDate("paytime"));
					o.setSendTime(rs.getDate("sendtime"));
					o.setCompleteTime(rs.getDate("completetime"));
					o.setState(rs.getInt("state"));
					o.setLogisticsCom(rs.getString("logisticscom"));
					o.setLogisticsNum(rs.getString("logisticsnum"));
					// 将对象加到集合中
					list.add((T) o);
				}
			} else if (t instanceof Helps) {
				list = (List<T>) new ArrayList<Helps>();
				sql = "select * from helps";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Helps h = new Helps();
					h.setId(rs.getInt("id"));
					h.setUserId(rs.getInt("userid"));
					h.setTitle(rs.getString("title"));
					h.setDetail(rs.getString("detail"));
					h.setPubTime(rs.getDate("pubtime"));
					h.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) h);
				}
			} else if (t instanceof Donates) {
				list = (List<T>) new ArrayList<Donates>();
				sql = "select * from donates";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Donates d = new Donates();
					d.setId(rs.getInt("id"));
					d.setHelpId(rs.getInt("helpid"));
					d.setUserId(rs.getInt("userid"));
					d.setDonTime(rs.getDate("dontime"));
					d.setLogisticsCom(rs.getString("logisticscom"));
					d.setLogisticsNum(rs.getString("logisticsnum"));
					// 将对象加到集合中
					list.add((T) d);
				}
			} else if (t instanceof GoodsReports) {
				list = (List<T>) new ArrayList<GoodsReports>();
				sql = "select * from goodsreports";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					GoodsReports gr = new GoodsReports();
					gr.setId(rs.getInt("id"));
					gr.setGoodId(rs.getInt("goodid"));
					gr.setBrief(rs.getString("brief"));
					gr.setRepTime(rs.getDate("reptime"));
					gr.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) gr);
				}
			} else if (t instanceof HelpsReports) {
				list = (List<T>) new ArrayList<HelpsReports>();
				sql = "select * from helpsreports";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					HelpsReports hr = new HelpsReports();
					hr.setId(rs.getInt("id"));
					hr.setHelpId(rs.getInt("helpid"));
					hr.setBrief(rs.getString("brief"));
					hr.setRepTime(rs.getDate("reptime"));
					hr.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) hr);
				}
			} else if (t instanceof Messages) {
				list = (List<T>) new ArrayList<Messages>();
				sql = "select * from messages";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Messages m = new Messages();
					m.setId(rs.getInt("id"));
					m.setGoodId(rs.getInt("goodid"));
					m.setSendId(rs.getInt("sendid"));
					m.setReceiveId(rs.getInt("receiveid"));
					m.setWords(rs.getString("words"));
					m.setSendTime(rs.getDate("sendtime"));
					// 将对象加到集合中
					list.add((T) m);
				}
			} else if (t instanceof PrizeGoods) {
				list = (List<T>) new ArrayList<PrizeGoods>();
				sql = "select * from prizegoods";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					PrizeGoods pg = new PrizeGoods();
					pg.setId(rs.getInt("id"));
					pg.setName(rs.getString("name"));
					pg.setDetail(rs.getString("detail"));
					pg.setPrice(rs.getInt("price"));
					pg.setCount(rs.getInt("count"));
					// 将对象加到集合中
					list.add((T) pg);
				}
			} else if (t instanceof GoodsImages) {
				list = (List<T>) new ArrayList<GoodsImages>();
				sql = "select * from goodsimages";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					GoodsImages gi = new GoodsImages();
					gi.setId(rs.getInt("id"));
					gi.setGoodId(rs.getInt("goodid"));
					gi.setUrl(rs.getString("url"));
					// 将对象加到集合中
					list.add((T) gi);
				}
			} else if (t instanceof HelpsImages) {
				list = (List<T>) new ArrayList<HelpsImages>();
				sql = "select * from helpsimages";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					HelpsImages hi = new HelpsImages();
					hi.setId(rs.getInt("id"));
					hi.setHelpId(rs.getInt("helpid"));
					hi.setUrl(rs.getString("url"));
					// 将对象加到集合中
					list.add((T) hi);
				}
			} else if (t instanceof PGoodsImages) {
				list = (List<T>) new ArrayList<PGoodsImages>();
				sql = "select * from pgoodsimages";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					PGoodsImages pgi = new PGoodsImages();
					pgi.setId(rs.getInt("id"));
					pgi.setpGoodId(rs.getInt("pgoodid"));
					pgi.setUrl(rs.getString("url"));
					// 将对象加到集合中
					list.add((T) pgi);
				}
			} else if (t instanceof Address) {
				list = (List<T>) new ArrayList<Address>();
				sql = "select * from address";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Address a = new Address();
					a.setId(rs.getInt("id"));
					a.setUserId(rs.getInt("userid"));
					a.setName(rs.getString("name"));
					a.setPhone(rs.getString("phone"));
					a.setAddress(rs.getString("address"));
					// 将对象加到集合中
					list.add((T) a);
				}
			} else if (t instanceof Collections) {
				list = (List<T>) new ArrayList<Collections>();
				sql = "select * from collections";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					Collections c = new Collections();
					c.setUserId(rs.getInt("userid"));
					c.setGoodId(rs.getInt("goodid"));
					c.setColTime(rs.getDate("coltime"));
					// 将对象加到集合中
					list.add((T) c);
				}
			} else if (t instanceof UserMarket) {
				list = (List<T>) new ArrayList<UserMarket>();
				sql = "select * from usermarket";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				while (rs.next()) {
					UserMarket um = new UserMarket();
					um.setUserId(rs.getInt("userid"));
					um.setMarketId(rs.getInt("marketid"));
					um.setFocTime(rs.getDate("foctime"));
					// 将对象加到集合中
					list.add((T) um);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally

		{
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list;

	}

	@Override
	public T getByID(T t, int id) {
		Connection conn;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		InputStream is;
		try {
			conn = DBConnection.getConnection();
			if (t instanceof Users) {
				sql = "select * from users where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Users) t).setId(rs.getInt("id"));
					((Users) t).setIdentity(rs.getString("identity"));
					((Users) t).setPwd(rs.getString("pwd"));
					if (rs.getBlob("photo") != null) {
						Blob photo_blob = rs.getBlob("photo");
						is = photo_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] bphoto = swapStream.toByteArray();
						((Users) t).setPhoto(bphoto);
					} else {
						((Users) t).setPhoto(null);
					}
					((Users) t).setName(rs.getString("name"));
					((Users) t).setSex(rs.getInt("sex"));
					((Users) t).setJifen(rs.getInt("jifen"));
					((Users) t).setInvCode(rs.getString("invcode"));
				}
			} else if (t instanceof Goods) {
				sql = "select * from goods where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Goods) t).setId(rs.getInt("id"));
					((Goods) t).setUserId(rs.getInt("userid"));
					((Goods) t).setName(rs.getString("name"));
					((Goods) t).setImformation(rs.getString("imformation"));
					((Goods) t).setTypeId(rs.getInt("typeid"));
					((Goods) t).setSoldPrice(rs.getDouble("soldprice"));
					((Goods) t).setBuyPrice(rs.getDouble("buyprice"));
					((Goods) t).setMarketId(rs.getInt("marketid"));
					((Goods) t).setLongitude(rs.getDouble("longitude"));
					((Goods) t).setLatitude(rs.getDouble("latitude"));
					((Goods) t).setPubTime(rs.getDate("pubtime"));
					((Goods) t).setState(rs.getInt("state"));
				}
			} else if (t instanceof Types) {
				sql = "select * from types where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Types) t).setId(rs.getInt("id"));
					((Types) t).setName(rs.getString("name"));
					if (rs.getBlob("inco") != null) {
						Blob inco_blob = rs.getBlob("inco");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] binco = swapStream.toByteArray();
						((Types) t).setInco(binco);
					} else {
						((Types) t).setInco(null);
					}
				}
			} else if (t instanceof Markets) {
				sql = "select * from markets where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Markets) t).setId(rs.getInt("id"));
					((Markets) t).setName(rs.getString("name"));
					if (rs.getBlob("logo") != null) {
						Blob inco_blob = rs.getBlob("logo");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] logo = swapStream.toByteArray();
						((Markets) t).setLogo(logo);
					} else {
						((Markets) t).setLogo(null);
					}
					((Markets) t).setBrief(rs.getString("brief"));
				}
			} else if (t instanceof Orders) {

			} else if (t instanceof Helps) {
				sql = "select * from helps where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Helps) t).setId(rs.getInt("id"));
					((Helps) t).setUserId(rs.getInt("userid"));
					((Helps) t).setTitle(rs.getString("title"));
					((Helps) t).setDetail(rs.getString("detail"));
					((Helps) t).setPubTime(rs.getDate("pubtime"));
					((Helps) t).setState(rs.getInt("state"));
				}
			} else if (t instanceof Donates) {
				sql = "select * from donates where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Donates) t).setId(rs.getInt("id"));
					((Donates) t).setHelpId(rs.getInt("helpid"));
					((Donates) t).setUserId(rs.getInt("userid"));
					((Donates) t).setDonTime(rs.getDate("dontime"));
					((Donates) t).setLogisticsCom(rs.getString("logisticscom"));
					((Donates) t).setLogisticsNum(rs.getString("logisticsnum"));
				}
			} else if (t instanceof GoodsReports) {
				sql = "select * from goodsreports where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((GoodsReports) t).setId(rs.getInt("id"));
					((GoodsReports) t).setGoodId(rs.getInt("goodid"));
					((GoodsReports) t).setBrief(rs.getString("brief"));
					((GoodsReports) t).setRepTime(rs.getDate("reptime"));
					((GoodsReports) t).setState(rs.getInt("state"));
				}
			} else if (t instanceof HelpsReports) {
				sql = "select * from helpsreports where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((HelpsReports) t).setId(rs.getInt("id"));
					((HelpsReports) t).setHelpId(rs.getInt("helpid"));
					((HelpsReports) t).setBrief(rs.getString("brief"));
					((HelpsReports) t).setRepTime(rs.getDate("reptime"));
					((HelpsReports) t).setState(rs.getInt("state"));
				}
			} else if (t instanceof Messages) {
				sql = "select * from messages where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Messages) t).setId(rs.getInt("id"));
					((Messages) t).setGoodId(rs.getInt("goodid"));
					((Messages) t).setSendId(rs.getInt("sendid"));
					((Messages) t).setReceiveId(rs.getInt("receiveid"));
					((Messages) t).setWords(rs.getString("words"));
					((Messages) t).setSendTime(rs.getDate("sendtime"));
				}
			} else if (t instanceof PrizeGoods) {
				sql = "select * from prizegoods where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((PrizeGoods) t).setId(rs.getInt("id"));
					((PrizeGoods) t).setName(rs.getString("name"));
					((PrizeGoods) t).setDetail(rs.getString("detail"));
					((PrizeGoods) t).setPrice(rs.getInt("price"));
					((PrizeGoods) t).setCount(rs.getInt("count"));
				}
			} else if (t instanceof GoodsImages) {
				sql = "select * from goodsimages where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((GoodsImages) t).setId(rs.getInt("id"));
					((GoodsImages) t).setGoodId(rs.getInt("goodid"));
					((GoodsImages) t).setUrl(rs.getString("url"));
				}
			} else if (t instanceof HelpsImages) {
				sql = "select * from helpsimages where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((HelpsImages) t).setId(rs.getInt("id"));
					((HelpsImages) t).setHelpId(rs.getInt("helpid"));
					((HelpsImages) t).setUrl(rs.getString("url"));
				}
			} else if (t instanceof PGoodsImages) {
				sql = "select * from pgoodsimages where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((PGoodsImages) t).setId(rs.getInt("id"));
					((PGoodsImages) t).setpGoodId(rs.getInt("pgoodid"));
					((PGoodsImages) t).setUrl(rs.getString("url"));
				}
			} else if (t instanceof Address) {
				sql = "select * from address where id =?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, id);
				rs = prep.executeQuery();
				while (rs.next()) {
					((Address) t).setId(rs.getInt("id"));
					((Address) t).setUserId(rs.getInt("userid"));
					((Address) t).setName(rs.getString("name"));
					((Address) t).setPhone(rs.getString("phone"));
					((Address) t).setAddress(rs.getString("address"));
				}
			} else if (t instanceof Collections) {

			} else if (t instanceof UserMarket) {

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			//
			if (conn != null)
				conn.close();
			if (prep != null)
				prep.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return t;
	}

	@Override
	public List<T> getPage(T t, int curPage, int pageSize) {
		List<T> list = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs;
		String sql;
		InputStream is = null;
		try {
			conn = DBConnection.getConnection();
			if (t instanceof Users) {
				list = (List<T>) new ArrayList<Users>();
				sql = "select * from users limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Users u = new Users();
					u.setId(rs.getInt("id"));
					u.setIdentity(rs.getString("identity"));
					u.setPwd(rs.getString("pwd"));
					if (rs.getBlob("photo") != null) {
						Blob photo_blob = rs.getBlob("photo");
						is = photo_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] bphoto = swapStream.toByteArray();
						u.setPhoto(bphoto);
					}else{
						u.setPhoto(null);						
					}
					u.setName(rs.getString("name"));
					u.setSex(rs.getInt("sex"));
					u.setJifen(rs.getInt("jifen"));
					u.setInvCode(rs.getString("invcode"));
					// 将对象加到集合中
					list.add((T) u);
				}
			} else if (t instanceof Goods) {
				list = (List<T>) new ArrayList<Goods>();
				sql = "select * from goods limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Goods g = new Goods();
					g.setId(rs.getInt("id"));
					g.setUserId(rs.getInt("userid"));
					g.setName(rs.getString("name"));
					g.setImformation(rs.getString("imformation"));
					g.setTypeId(rs.getInt("typeid"));
					g.setSoldPrice(rs.getDouble("soldprice"));
					g.setBuyPrice(rs.getDouble("buyprice"));
					g.setMarketId(rs.getInt("marketid"));
					g.setLongitude(rs.getDouble("longitude"));
					g.setLatitude(rs.getDouble("latitude"));
					g.setPubTime(rs.getDate("pubtime"));
					g.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) g);
				}
			} else if (t instanceof Types) {
				list = (List<T>) new ArrayList<Types>();
				sql = "select * from types limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Types types = new Types();
					types.setId(rs.getInt("id"));
					types.setName(rs.getString("name"));
					if (rs.getBlob("inco") != null) {
						Blob inco_blob = rs.getBlob("inco");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] binco = swapStream.toByteArray();
						types.setInco(binco);
					}else{
						types.setInco(null);						
					}
					// 将对象加到集合中
					list.add((T) types);
				}
			} else if (t instanceof Markets) {
				list = (List<T>) new ArrayList<Markets>();
				sql = "select * from markets limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Markets m = new Markets();
					m.setId(rs.getInt("id"));
					m.setName(rs.getString("name"));
					if (rs.getBlob("logo") != null) {
						Blob inco_blob = rs.getBlob("logo");
						is = inco_blob.getBinaryStream();
						// blob->InputStream->byte[]
						ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
						byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
						int rc = 0;
						while ((rc = is.read(buff, 0, 100)) > 0) {
							swapStream.write(buff, 0, rc);
						}
						byte[] logo = swapStream.toByteArray();
						m.setLogo(logo);
					}else{
						m.setLogo(null);						
					}
					m.setBrief(rs.getString("brief"));
					// 将对象加到集合中
					list.add((T) m);
				}
			} else if (t instanceof Orders) {
				list = (List<T>) new ArrayList<Orders>();
				sql = "select * from orders limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Orders o = new Orders();
					o.setId(rs.getInt("id"));
					o.setGoodId(rs.getInt("goodid"));
					o.setUserId(rs.getInt("userid"));
					o.setOrderNum(rs.getString("ordernum"));
					o.setCreateTime(rs.getDate("createtime"));
					o.setPayTime(rs.getDate("paytime"));
					o.setSendTime(rs.getDate("sendtime"));
					o.setCompleteTime(rs.getDate("completetime"));
					o.setState(rs.getInt("state"));
					o.setLogisticsCom(rs.getString("logisticscom"));
					o.setLogisticsNum(rs.getString("logisticsnum"));
					// 将对象加到集合中
					list.add((T) o);
				}
			} else if (t instanceof Helps) {
				list = (List<T>) new ArrayList<Helps>();
				sql = "select * from helps limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Helps h = new Helps();
					h.setId(rs.getInt("id"));
					h.setUserId(rs.getInt("userid"));
					h.setTitle(rs.getString("title"));
					h.setDetail(rs.getString("detail"));
					h.setPubTime(rs.getDate("pubtime"));
					h.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) h);
				}
			} else if (t instanceof Donates) {
				list = (List<T>) new ArrayList<Donates>();
				sql = "select * from donates limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Donates d = new Donates();
					d.setId(rs.getInt("id"));
					d.setHelpId(rs.getInt("helpid"));
					d.setUserId(rs.getInt("userid"));
					d.setDonTime(rs.getDate("dontime"));
					d.setLogisticsCom(rs.getString("logisticscom"));
					d.setLogisticsNum(rs.getString("logisticsnum"));
					// 将对象加到集合中
					list.add((T) d);
				}
			} else if (t instanceof GoodsReports) {
				list = (List<T>) new ArrayList<GoodsReports>();
				sql = "select * from goodsreports limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					GoodsReports gr = new GoodsReports();
					gr.setId(rs.getInt("id"));
					gr.setGoodId(rs.getInt("goodid"));
					gr.setBrief(rs.getString("brief"));
					gr.setRepTime(rs.getDate("reptime"));
					gr.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) gr);
				}
			} else if (t instanceof HelpsReports) {
				list = (List<T>) new ArrayList<HelpsReports>();
				sql = "select * from helpsreports limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					HelpsReports hr = new HelpsReports();
					hr.setId(rs.getInt("id"));
					hr.setHelpId(rs.getInt("helpid"));
					hr.setBrief(rs.getString("brief"));
					hr.setRepTime(rs.getDate("reptime"));
					hr.setState(rs.getInt("state"));
					// 将对象加到集合中
					list.add((T) hr);
				}
			} else if (t instanceof Messages) {
				list = (List<T>) new ArrayList<Messages>();
				sql = "select * from messages limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					Messages m = new Messages();
					m.setId(rs.getInt("id"));
					m.setGoodId(rs.getInt("goodid"));
					m.setSendId(rs.getInt("sendid"));
					m.setReceiveId(rs.getInt("receiveid"));
					m.setWords(rs.getString("words"));
					m.setSendTime(rs.getDate("sendtime"));
					// 将对象加到集合中
					list.add((T) m);
				}
			} else if (t instanceof PrizeGoods) {
				list = (List<T>) new ArrayList<PrizeGoods>();
				sql = "select * from prizegoods limit ?,?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, (curPage - 1) * pageSize);
				prep.setInt(2, pageSize);
				rs = prep.executeQuery();
				while (rs.next()) {
					PrizeGoods pg = new PrizeGoods();
					pg.setId(rs.getInt("id"));
					pg.setName(rs.getString("name"));
					pg.setDetail(rs.getString("detail"));
					pg.setPrice(rs.getInt("price"));
					pg.setCount(rs.getInt("count"));
					// 将对象加到集合中
					list.add((T) pg);
				}
			} else if (t instanceof GoodsImages) {

			} else if (t instanceof HelpsImages) {

			} else if (t instanceof PGoodsImages) {

			} else if (t instanceof Address) {

			} else if (t instanceof Collections) {

			} else if (t instanceof UserMarket) {

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally

		{
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	@Override
	public int getCount(T t) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs;
		int count = 0;
		String sql = null;
		try {
			conn = DBConnection.getConnection();
			if (t instanceof Users) {
				sql = "select count(*) c from users";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}

			} else if (t instanceof Goods) {
				sql = "select count(*) c from goods";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Types) {
				sql = "select count(*) c from types";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Markets) {
				sql = "select count(*) c from markets";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Orders) {
				sql = "select count(*) c from orders";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Helps) {
				sql = "select count(*) c from helps";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Donates) {
				sql = "select count(*) c from donates";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof GoodsReports) {
				sql = "select count(*) c from goodsreports";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof HelpsReports) {
				sql = "select count(*) c from helpsreports";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Messages) {
				sql = "select count(*) c from messages";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof PrizeGoods) {
				sql = "select count(*) c from prizeGoods";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof GoodsImages) {

			} else if (t instanceof HelpsImages) {

			} else if (t instanceof PGoodsImages) {

			} else if (t instanceof Address) {
				sql = "select count(*) c from address";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof Collections) {
				sql = "select count(*) c from collections";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			} else if (t instanceof UserMarket) {
				sql = "select count(*) c from usermarket";
				prep = conn.prepareStatement(sql);
				rs = prep.executeQuery();
				if (rs.next()) {
					count = rs.getInt("c");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return count;
	}

}
