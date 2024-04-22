package com.general.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.general.dao.ProductDao;
import com.general.Factory.ConnectionFactory;
import com.general.Factory.ProductDaoFactory;
import com.general.bean.Order;
import com.general.bean.Product;

public class OrderDaoImpl implements OrderDao {

	PreparedStatement pst = null;
	ResultSet rs = null;
	Connection connection = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
	@Override
	public boolean addOrder(Order order) {
	
		boolean result = false;
		
		try {
			connection = ConnectionFactory.getConnection();
			pst = connection.prepareStatement("insert into orderList values(?,?,?,?,?)");  
			pst.setInt(1, order.getOrderId()); 
			pst.setInt(2, order.getPid());
			pst.setInt(3, order.getQuantity()); 
			pst.setInt(4, order.getUserId()); 
			java.util.Date date = sdf.parse(order.getDate());
			long l = date.getTime();
			java.sql.Date date1=new java.sql.Date(l);
			pst.setDate(5, date1);   
			int rs = pst.executeUpdate();
			
			if(rs>0) {
				result = true;
			}
			else 
				result = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Order> userOrders(int userId){
		List<Order> list = new ArrayList<Order>();
		try {
			connection = ConnectionFactory.getConnection();
			pst = connection.prepareStatement("select * from orderList where c_id=? order by orderList.order_id desc");
			pst.setInt(1, userId); 
			rs = pst.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				ProductDao productDao = ProductDaoFactory.getDao();
				int pId = rs.getInt("p_id");
				
				Product product = productDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("order_id"));  
				order.setPid(pId);
				order.setPname(product.getPname());  
				order.setCategory(product.getCategory()); 
				order.setPrice(product.getPrice()*rs.getInt("quantity"));   
				order.setQuantity(rs.getInt("quantity")); 
				java.sql.Date sdate = rs.getDate("orderDate"); 
				order.setDate(sdf.format(sdate)); 
				list.add(order);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	public void cancelOrder(int orderId) {
	
		try {
			connection = ConnectionFactory.getConnection();
			pst = connection.prepareStatement("delete from orderList where order_id = ?");
			pst.setInt(1, orderId); 
			pst.execute(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
