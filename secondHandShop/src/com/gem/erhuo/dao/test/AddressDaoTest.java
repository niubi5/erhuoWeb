package com.gem.erhuo.dao.test;

import org.junit.Test;

import com.gem.erhuo.dao.AddressDao;
import com.gem.erhuo.entity.Address;

public class AddressDaoTest {
	
	 AddressDao ad=new AddressDao();
     @Test
     public void getUserAddressByPhone(){
    	 Address ads=ad.getUserAddressByUserId("1");
    	 System.out.println(ads);
     }
   
}
