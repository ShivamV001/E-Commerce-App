package com.general.dao;

import java.util.List;

import com.general.bean.Order;

public interface OrderDao {
public boolean addOrder(Order order);
public List<Order> userOrders(int id); 
public void cancelOrder(int o_id);
}
