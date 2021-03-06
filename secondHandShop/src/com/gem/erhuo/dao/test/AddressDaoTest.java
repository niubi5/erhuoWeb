package com.gem.erhuo.dao.test;

import org.junit.Test;

import com.gem.erhuo.dao.AddressDao;
import com.gem.erhuo.entity.Address;

public class AddressDaoTest {
	
	 AddressDao ad=new AddressDao();
     @Test
     public void getUserAddressByUserid(){
    	 Address ads=ad.getUserAddressByUserId("6");
    	 System.out.println(ads);
     }
   
     @Test
     public void saveAddress(){
    	 Address adds=new Address();
    	 adds.setUserId(7);
    	 adds.setName("雷克顿");
    	 adds.setPhone("15911119999");
    	 adds.setAddress("湖北省武汉市汉阳镇博学路江大五号门");
    	 adds.setIsdefault("no");
    	 ad.saveAddress(adds);
     }
     
     @Test
     public void updateAddressIsdefault(){
    	 ad.updateAddressIsdefault(1);
     }
     
     @Test
     public void updateAddress(){
    	 Address adds=new Address();
    	 adds.setId(11);
    	 adds.setUserId(7);
    	 adds.setName("雷克顿");
    	 adds.setPhone("15911112332");
    	 adds.setAddress("湖北省武汉市汉阳镇博学路江大五号门");
    	 adds.setIsdefault("yes");
    	 ad.updateAddress(adds);
     }
     @Test
     public Address getUserAddressById(){
    	 return ad.getUserAddressById(1);
     }
}
