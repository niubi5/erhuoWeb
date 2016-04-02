package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.entity.Goods;
import com.gem.erhuo.entity.Users;
import com.gem.erhuo.service.AddressService;
import com.gem.erhuo.service.UserService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserInformationServlet
 */
public class UserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		System.out.println(name);
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String userid=request.getParameter("userid");
		UserService service=new UserService();
		AddressService addressService=new AddressService();
		//修改用户表
		service.updateUser(name, phone, sex);
		//修改默认地址
		addressService.updateUserAddress(address, userid);
		//获取用户对象
		Users users=service.getUserByIdentity(phone);
		//获取地址对像
		Address userAddress=addressService.getUserAddressByPhoneService(userid);
		Map<Address, Users> UsersAddress = new HashMap<Address, Users>();
		UsersAddress.put(userAddress, users);
		String userStr = null;
		if(users != null && userAddress!=null){
			
			userStr = new Gson().toJson(UsersAddress);
		}
		PrintWriter pw = response.getWriter();
		pw.print(userStr);
		System.out.println(userStr);
		pw.close();
	}

}

