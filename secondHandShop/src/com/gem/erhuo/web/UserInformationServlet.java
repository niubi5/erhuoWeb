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
import com.google.gson.GsonBuilder;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
		String address = new String(request.getParameter("address").getBytes("iso-8859-1"), "utf-8");
		String phone = new String(request.getParameter("phone").getBytes("iso-8859-1"), "utf-8");
		String sex = new String(request.getParameter("sex").getBytes("iso-8859-1"), "utf-8");
		String userid = new String(request.getParameter("userid").getBytes("iso-8859-1"), "utf-8");
		System.out.println(name);
		System.out.println(address);
		System.out.println(phone);
		System.out.println(sex);
		System.out.println(userid);
		UserService service = new UserService();
		AddressService addressService = new AddressService();
		// 修改用户表
		service.updateUser(name, phone, sex);
		// 修改默认地址
		addressService.updateUserAddress(address, userid);
		// 获取用户对象
		Users users = service.getUserByIdentity(phone);
		System.out.println(users);
		// 获取地址对像
		Address userAddress = addressService.getUserAddressByPhoneService(userid);
		if (userAddress==null) {
				Address ads=new Address();
				ads.setAddress(address);
				ads.setUserId(Integer.parseInt(userid));
				ads.setIsdefault("yes");
				ads.setName(name);
				ads.setPhone(phone);
				addressService.saveAddress(ads);
				userAddress = addressService.getUserAddressByPhoneService(userid);
		}
		System.out.println(userAddress);
		Map<Address, Users> UsersAddress = new HashMap<Address, Users>();
		UsersAddress.put(userAddress, users);
		String userStr = null;
		if (users != null && userAddress != null) {
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			userStr = gson.toJson(UsersAddress);
		}
		PrintWriter pw = response.getWriter();
		pw.print(userStr);
		System.out.println(userStr);
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
