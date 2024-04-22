package com.general.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.general.Factory.ConnectionFactory;
import com.general.bean.Cart;
import com.general.bean.Product;

public class ProductDaoImpl implements ProductDao {

	Connection connection=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	@Override
	public List<Product> getAllProoducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			connection = ConnectionFactory.getConnection();
			pst = connection.prepareStatement("select * from products");
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setPid(rs.getInt("p_id")); 
				row.setPname(rs.getString("p_name"));  
				row.setCategory(rs.getString("category"));  
				row.setPrice(rs.getFloat("price"));  
				row.setImage(rs.getString("image"));   
				products.add(row);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	//		ConnectionFactory.cleanUp();
		}
		return products;
	} 
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					connection = ConnectionFactory.getConnection();
					pst = connection.prepareStatement("select * from Products where p_id = ?");
					pst.setInt(1, item.getPid()); 
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart cart = new Cart();
						cart.setPid(rs.getInt("p_id")); 
						cart.setPname(rs.getString("p_name"));   
						cart.setCategory(rs.getString("category"));  
						cart.setPrice(rs.getFloat("price"));  
						cart.setQuantity(item.getQuantity());  
						products.add(cart);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	//		ConnectionFactory.cleanUp();
		}
		return products;
	}
	public Float getCartTotalPrice(ArrayList<Cart> cart_list) {
		Float sum=0.0f;
		try {
			for(Cart c:cart_list) {
				connection = ConnectionFactory.getConnection();
				pst = connection.prepareStatement("select price from products where p_id =?");
				pst.setFloat(1, c.getPid());
				rs = pst.executeQuery();
				while(rs.next()) {
					 sum += rs.getFloat("price")*c.getQuantity();
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Product getSingleProduct(int pid) {
		Product row = null;
		try {
			connection = ConnectionFactory.getConnection();
			pst = connection.prepareStatement("select * from products where p_id = ?");
			pst.setInt(1, pid); 
			rs = pst.executeQuery();
			while(rs.next()) {
				row = new Product();
				row.setPid(rs.getInt("p_id"));   
				row.setPname(rs.getString("p_name"));  
				row.setCategory(rs.getString("category"));  
				row.setPrice(rs.getFloat("price"));  
				row.setImage(rs.getString("image"));   
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}

}