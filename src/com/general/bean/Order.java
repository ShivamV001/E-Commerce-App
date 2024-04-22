package com.general.bean;


public class Order extends Product {
private int orderId;
private int userId;
private int quantity;
private String date;
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}


}