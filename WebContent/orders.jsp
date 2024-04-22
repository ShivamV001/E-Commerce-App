<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.general.Factory.*,com.general.bean.*,java.util.*" %> 

<% Customer user = (Customer)request.getSession(false).getAttribute("log"); 
List<Order> orders = null;
 if(user!=null){
	 request.setAttribute("log",user);
	  orders = OrderDaoFactory.getDao().userOrders(user.getId());
	 request.getSession(false).setAttribute("orders", orders); 
 }

 ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession(false).getAttribute("cart_list");
 if(cart_list!=null){
	 request.setAttribute("cart_list", cart_list);
 }
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="orderstyle.css"> 
<meta charset="ISO-8859-1">
<title>Your Orders</title>
</head>
<body>
<%@include file="Navbar.jsp" %> 
<div id="orderpage">
<p>All Orders</p>
<table class="order">
<tr>
<th>Order Date</th>
<th>Product</th>
<th>Category</th>
<th>Quantity</th>
<th>Price</th>
<th>Cancel</th>
</tr>
<% 
   if(orders !=null){
	   for(Order o:orders){
%>
<tr>
<td><%= o.getDate() %></td>
<td><%= o.getPname() %></td>
<td><%= o.getCategory() %></td>
<td><%= o.getQuantity() %></td>
<td><%= o.getPrice() %></td>
<td><a href="./cancel-order?order_id=<%=o.getOrderId() %>" id="cancel-o">Cancel</a></td>
<% } } %>
</tr>
</table>
</div>
</body>
</html>