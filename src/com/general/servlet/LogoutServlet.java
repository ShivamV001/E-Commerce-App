package com.general.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         if( request.getSession().getAttribute("log")!=null) {
        	 request.getSession().removeAttribute("log");
        	 response.sendRedirect("login.jsp"); 
         }
         else {
        	 response.sendRedirect("index.jsp"); 
         }
	}

}