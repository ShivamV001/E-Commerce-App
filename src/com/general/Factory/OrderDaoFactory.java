package com.general.Factory;

import com.general.dao.OrderDao;
import com.general.dao.OrderDaoImpl;

public class OrderDaoFactory {
private static OrderDao orderDao;
static {
	orderDao = new OrderDaoImpl();
}
public static OrderDao getDao() {
	return orderDao;
}
}
