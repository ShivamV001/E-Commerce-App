<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.general.Factory.* ,com.general.dao.ProductDao, java.util.List, com.general.bean.*" %>

<% Customer var = (Customer)request.getSession().getAttribute("log"); 
 if(var!=null){
	 request.setAttribute("log",var);
 }
%>
<% 
ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart_list");
if(cart_list!=null){
	request.setAttribute("cart_list", cart_list);
	ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
	 request.setAttribute("orders", orderList);
}
%>
<%ProductDao dao = ProductDaoFactory.getDao(); 
List<Product> products = dao.getAllProoducts();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="indexstyle.css">
<meta charset="ISO-8859-1">
<title>Index Page</title>
</head>
<body>
<%@ include file="Navbar.jsp" %>
<div class="main">
<% 
if( !products.isEmpty()){
	for(Product p : products){
%>
<div class="card-container">
<div class="card-box">
<img src="images/<%=p.getImage() %>" alt="watch"/>
</div>
<div class="content">
<h5 style="font-size: 21px;"><%=p.getPname() %></h5>
<h5><%=p.getCategory() %></h5>
<span style="font-size: 20px;">&#8377</span><h5 style="display:inline; font-size:18px;"><%=p.getPrice() %></h5> 
</div>
<div id="button">
<a href="addtocart?pid=<%=p.getPid()%>" style="color:white; background-color: black;">Add to cart</a>
<a href="buy-now?pid=<%=p.getPid() %>" style="color:white; background-color: dodgerBlue;">Buy now</a>
</div>
</div>
<% } } %>
</div>
</body>
</html>