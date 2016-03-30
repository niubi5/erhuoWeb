package com.gem.erhuo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.erhuo.entity.Address;
import com.gem.erhuo.service.AddressService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddressListServlet
 */
public class AddressListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userid=request.getParameter("curUserId");
		AddressService service=new AddressService();
		List<Address> address=service.getListAddressByUserId(userid);
		if (address!=null) {
			Gson gson = new Gson();
			String str = gson.toJson(address);
			PrintWriter pw=response.getWriter();
			pw.write(str);
			pw.close();
		}
		
		
		
	}

}
