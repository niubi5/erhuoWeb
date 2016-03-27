package com.gem.erhuo.service;

import com.gem.erhuo.dao.AddressDao;
import com.gem.erhuo.entity.Address;

public class AddressService {
	private AddressDao ad=new AddressDao();
	//查找地址
	public Address getUserAddressByPhoneService(String userid){
		return ad.getUserAddressByUserId(userid);
		
	}
	public void saveAddress(Address address){
		ad.saveAddress(address);
	}
	
	
	
}
