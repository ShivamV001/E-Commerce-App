<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.general.bean.*, java.util.*, com.general.dao.ProductDao, com.general.dao.ProductDaoImpl" %>
<% Customer user = (Customer)request.getSession(false).getAttribute("log"); 
 if(user!=null){
	 request.setAttribute("log",user);
 }
%>
<% 
ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession(false).getAttribute("cart_list");
List<Cart> cartProduct = null;
if(cart_list!=null){
	ProductDaoImpl pdao = new ProductDaoImpl();
	cartProduct = pdao.getCartProducts(cart_list);
	float total = pdao.getCartTotalPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total",total);
	ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
	 request.setAttribute("orders", orderList);
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cartstyle.css">
<meta charset="ISO-8859-1">
<title>Cart Page</title>
</head>
<body>
<%@ include file="Navbar.jsp" %>
<div id="total"><h1>Total Price: <%=request.getAttribute("total") %></h1><a href="./check-out" class="checkout">Check Out</a></div>
<table id="tab">
<tr>
<th>Product</th>
<th>Category</th>
<th>Price</th>
<th>Buy Now</th>
<th>Cancel</th>
</tr>
<tr>
<% if(cart_list!=null){
	for(Cart c:cartProduct){
%>
<td><%=c.getPname() %></td>
<td><%=c.getCategory() %></td>
<td><span style="font-size: 20px; margin-right: 7px;">&#8377</span><% out.print(c.getPrice()*c.getQuantity()); %></td>
<td><form method="post" action="./buy-now">
<input type="hidden" name="pid" value="<%=c.getPid() %>" />
<div class="form-group">
<a href="quantity-inc-dec?action=dec&id=<%=c.getPid() %>">-</a>
<input type="text" name="quantity" value="<%= c.getQuantity() %>" readonly> 
<a href="quantity-inc-dec?action=inc&id=<%=c.getPid() %>">+</a>
<input type="submit" value="Buy" style="border: none; color:white; background-color:dodgerblue; padding: 5px; box-sizing:border-box; box-shadow: 3px 3px 5px 2px lightgrey;"/>
</div>
</form>
</td>
<td><a href="remove-cart?pid=<%=c.getPid() %>". class="cancel">Remove</a></td>
</tr>
<% } } %>
</body>
</html>