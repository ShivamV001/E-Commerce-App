package com.general.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general.bean.Cart;

@WebServlet("/remove-cart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("pid")); 
		ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart_list");
		if(cart_list!=null) {
			for(Cart c:cart_list) {
				if(c.getPid()==id) {
					cart_list.remove(cart_list.indexOf(c));
					break;
				}
			}
			response.sendRedirect("cart.jsp"); 
		}
		else {
			response.sendRedirect("cart.jsp"); 
		}
	}

}
