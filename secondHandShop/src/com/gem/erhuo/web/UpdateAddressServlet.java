package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.service.AddressService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateAddressServlet
 */
public class UpdateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAddressServlet() {
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
		String ads=request.getParameter("address");
		Address address=new Gson().fromJson(ads, Address.class);
		AddressService service=new AddressService();
		System.out.println(ads);
		int userid=address.getUserId();
		if ((address.getIsdefault().toString()).equals("yes")) {
			service.updateAddressIsdefault(userid);
		}
		service.updateAddress(address);
		Address As=service.getUserAddressById(address.getId());
		if (As!=null&&!As.equals("null")) {
			PrintWriter pw = response.getWriter();
			pw.print("save ok");
			pw.close();
		}
		
	}

}
