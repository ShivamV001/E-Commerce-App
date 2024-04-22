<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" session="true"%> 
<%@ page import="java.util.*, com.general.bean.*" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="styleSheet" href="navstyle.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="navbar">
<ul>
<li style="margin:0 auto 0 30px">City Cart</li>
<li><a href="index.jsp">Home
<li><a href="cart.jsp">Cart
<% ArrayList<Cart> cartList = (ArrayList<Cart>)request.getAttribute("cart_list"); 
ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
request.setAttribute("orders", orderList);
if(cartList!=null) { %>
<span style="background-color:red; color:white; margin: 0; padding:5px 10px;"><%= cartList.size() %> </span>
<% } %> 
</a></li>
<% Customer log=(Customer)session.getAttribute("log"); 
if(log!=null){  %> 
<li><a href="orders.jsp">Orders</a></li> 
<li><a href="./logout">Logout</a></li>
<%  } else { %>
<li><a href="login.jsp">Login</a></li>
<%} %>
</ul>
</div>
</body>
</html>