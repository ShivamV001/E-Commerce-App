package com.general.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/buy-now")
public class BuynowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		PrintWriter out = response.getWriter();
		response.setContentType("text/html"); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		
		Customer log = (Customer)request.getSession().getAttribute("log");
	     if(log!=null) {
	    	 int productId = Integer.parseInt(request.getParameter("pid")); 
	    	 int quantity = Integer.parseInt(request.getParameter("quantity"));
	 			List<Order> orders = (ArrayList<Order>)request.getSession(false).getAttribute("orders"); 
	 			request.setAttribute("orders", orders); 
	    	 if(quantity<=0) {
	    		 quantity = 1;
	    	 }
	    	 Order order = new Order();
	    	 order.setPid(productId); 
	    	 order.setUserId(log.getId()); 
	    	 order.setQuantity(quantity);
	    	 order.setDate(sdf.format(date)); 
	    	 OrderDao orderDao = OrderDaoFactory.getDao();
	    	 boolean result = orderDao.addOrder(order);
	    	 
	    	 if(result) {
	    		 ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession(false).getAttribute("cart_list");
	    			if(cart_list!=null) {
	    				for(Cart c:cart_list) {
	    					if(c.getPid()==productId) {
	    						cart_list.remove(cart_list.indexOf(c));
	    						break;
	    					}
	    				}
	    				response.sendRedirect("orders.jsp");
	    			}
	    			
	    	 }
	    	 else {
	    		response.sendRedirect("error.jsp"); 
	    	 }
	     }
	     else {
	    	 response.sendRedirect("login.jsp"); 
	     }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
