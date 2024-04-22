package com.general.dao;
import java.util.ArrayList;
import java.util.List;

import com.general.bean.Cart;
import com.general.bean.Product;

public interface ProductDao {
public List<Product> getAllProoducts();
public List<Cart> getCartProducts(ArrayList<Cart> cartProduct);
public Product getSingleProduct(int pid);
}
