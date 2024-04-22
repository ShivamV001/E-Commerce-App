package com.general.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general.Factory.OrderDaoFactory;
import com.general.bean.Customer;
import com.general.bean.Order;
import com.general.dao.OrderDao;


@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer log = (Customer)request.getSession(false).getAttribute("log");
		int orderId = Integer.parseInt(request.getParameter("order_id")); 
		ArrayList<Order> orderList = (ArrayList<Order>) request.getSession(false).getAttribute("orders");
		request.setAttribute("orders", orderList);  
		if(log!=null) { 
			if(orderId>0) {
				for(Order o : orderList) {
					OrderDao orderDao = OrderDaoFactory.getDao();
					orderDao.cancelOrder(orderId);
					orderList.remove(orderList.indexOf(o));
					break;
				}
			} 
			response.sendRedirect("orders.jsp");
		} 
		else
			response.sendRedirect("login.jsp"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
