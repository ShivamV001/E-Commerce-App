package com.general.Factory;

import com.general.dao.CustomerDao;
import com.general.dao.CustomerDaoImpl;

public class CustomerDaoFactory {
private static CustomerDao customerDao;
static {
	customerDao = new CustomerDaoImpl();
}
public static CustomerDao getDao() {
	return customerDao;
}
}
