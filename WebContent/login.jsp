<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.general.bean.Customer" %>
  
  <% 
ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart_list");
if(cart_list!=null){
	request.setAttribute("cart_list", cart_list);
}
%>    
 <% Customer var=(Customer)session.getAttribute("log"); 
if(var!=null){ 
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="loginstyle.css">
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<%@ include file="Navbar.jsp" %>
<div class="form-container">
<form method="post" action="./customer" class="login-form">
<h1>Login Form</h1>
<input type="text" name="email" placeholder="Enter username"  class="box" required><br>
<input type="password" name="password" placeholder="Enter password" class="box" required><br>
<% String error = request.getParameter("error");
if(error!=null && error.equals("1") ) { 
%>
<p style="color:red;padding:0; margin:0;box-sizing:border-box;">Invalid username or password</p>
<% } %>
<button type="submit">Login</button>
</form>
</div>
</body>
</html>