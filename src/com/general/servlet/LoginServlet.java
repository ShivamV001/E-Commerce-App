package com.general.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general.Factory.CustomerDaoFactory;
import com.general.bean.Customer;
import com.general.dao.CustomerDao;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

@WebServlet("/customer")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Customer customer = new Customer();
		customer.setEmail(email); 
		customer.setPassword(password); 
		CustomerDao dao = CustomerDaoFactory.getDao();
		Customer status=dao.checkLogin(customer);
		if(status!=null) {
			request.getSession().setAttribute("log", status); 
			response.sendRedirect("index.jsp"); 
		}
		else if(status==null){ 
			response.sendRedirect("login.jsp?error=1"); 
		}
		else {
			out.println("Something went wrong");
		}
	}

}
