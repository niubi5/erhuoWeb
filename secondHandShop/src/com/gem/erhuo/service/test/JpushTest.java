package com.gem.erhuo.service.test;

import org.junit.Test;

import com.gem.erhuo.api.JPush;

public class JpushTest {
	private static final String appKey ="ebdc00513e108ac73f607987";  
    private static final String masterSecret = "9a7a81be9b20fa72f91f7b5b"; 
	
	@Test
	public void pushTest(){
		JPush.sendPush();  
        System.out.println("sucess");
	}
}
