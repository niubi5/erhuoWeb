package com.gem.erhuo.service;

import com.gem.erhuo.dao.AddressDao;
import com.gem.erhuo.entity.Address;

public class AddressService {
	private AddressDao ad=new AddressDao();
	//查找地址
	public Address getUserAddressByPhoneService(String phone){
		return ad.getUserAddressByPhone(phone);
		
	}

}
