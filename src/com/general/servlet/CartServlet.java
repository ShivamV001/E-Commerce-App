package com.general.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.general.bean.Cart;
import com.general.bean.Order;

import java.io.PrintWriter;

@WebServlet("/addtocart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
   protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		
		int id = Integer.parseInt(request.getParameter("pid")); 
		Cart cd = new Cart();
		cd.setPid(id); 
		cd.setQuantity(1); 
		
		HttpSession session = request.getSession();
		ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart_list");
		ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
		 request.setAttribute("orders", orderList);
		
		if(cart_list == null) {
			cartList.add(cd);
			session.setAttribute("cart_list", cartList); 
			response.sendRedirect("index.jsp"); 
 		}
		else {
			cartList = cart_list;
			boolean exist = false;
			
			for(Cart c:cartList) {
				if(c.getPid()==id) {
					exist = true;
					out.println("<html><body><h1>Product added already<a href='index.jsp'>go back to previous page</a></h1></body></html>");
				} 
			}
			if(!exist) {
				cartList.add(cd);
				response.sendRedirect("index.jsp"); 
			} 
		}
		
	}
}