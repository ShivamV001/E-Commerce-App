package com.general.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.general.Factory.ConnectionFactory;
import com.general.bean.Customer;

public class CustomerDaoImpl implements CustomerDao {
	
	PreparedStatement pst=null;
	Connection connection=null;
	String status="";
	
	@Override
	public Customer checkLogin(Customer customer) {
		try {
			connection=ConnectionFactory.getConnection();
			pst=connection.prepareStatement("select * from Customer where email = ?");
			pst.setString(1, customer.getEmail()); 
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				if(customer.getEmail().equalsIgnoreCase(rs.getString("email")) && customer.getPassword().equals(rs.getString("password"))) {
					customer.setEmail(rs.getString("email"));  
					customer.setName(rs.getString("c_name")); 
					customer.setPassword(rs.getString("password"));  
					customer.setId(rs.getInt("c_id"));  
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
//			ConnectionFactory.cleanUp();
		}
		return customer;
	}

}