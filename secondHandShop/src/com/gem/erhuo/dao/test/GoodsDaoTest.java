package com.gem.erhuo.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.erhuo.dao.GoodsDao;
import com.gem.erhuo.entity.Goods;

public class GoodsDaoTest {
	GoodsDao gd=new GoodsDao();
    @Test
	public void getGoodsList(){
    	List<Goods> list=gd.getGoodsList("电脑",1,2);
    	System.out.println(list);
    }
}
