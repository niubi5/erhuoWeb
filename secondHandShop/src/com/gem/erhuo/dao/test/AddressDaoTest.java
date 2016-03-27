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
   
     @Test
     public void saveAddress(){
    	 Address adds=new Address();
    	 adds.setUserId(7);
    	 adds.setName("雷克顿");
    	 adds.setPhone("15911112222");
    	 adds.setAddress("湖北省武汉市汉阳镇博学路江大五号门");
    	 adds.setIsdefault("yes");
    	 ad.saveAddress(adds);
     }
     
     @Test
     public void updateAddressIsdefault(){
    	 ad.updateAddressIsdefault("1");
     }
}
