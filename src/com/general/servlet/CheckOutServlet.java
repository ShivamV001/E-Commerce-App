package com.general.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general.Factory.OrderDaoFactory;
import com.general.bean.Cart;
import com.general.bean.Customer;
import com.general.bean.Order;
import com.general.dao.OrderDao;

@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
		Date date = new Date();
		
		Customer customer = (Customer)request.getSession(false).getAttribute("log");
		ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
		 request.setAttribute("orders", orderList);
			
		ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession(false).getAttribute("cart_list"); 
		if(customer!=null && cart_list!=null) {
			for(Cart c:cart_list) {
				Order order = new Order();
				order.setPid(c.getPid()); 
				order.setUserId(customer.getId()); 
				order.setQuantity(c.getQuantity()); 
				order.setDate( sdf.format(date));  
				OrderDao orderDao = OrderDaoFactory.getDao();
				boolean result = orderDao.addOrder(order);
				if(!result)
					break;
			}
			cart_list.clear();
			response.sendRedirect("orders.jsp"); 
		}
		else {
			if(customer==null)
				response.sendRedirect("login.jsp"); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
