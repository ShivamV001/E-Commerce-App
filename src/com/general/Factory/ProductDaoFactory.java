package com.general.Factory;

import com.general.dao.ProductDao;
import com.general.dao.ProductDaoImpl;

public class ProductDaoFactory {
private static ProductDao productDao;
static {
	productDao = new ProductDaoImpl();
}
public static ProductDao getDao() {
	return productDao;
}
}
