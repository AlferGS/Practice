//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.CustomerOrders;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CustomerOrdersRowMapper implements RowMapper<CustomerOrders> {
//
//    public CustomerOrders mapRow(ResultSet rs, int rowNum) throws SQLException{
//        CustomerOrders customerOrders = new CustomerOrders();
//        customerOrders.setCustom_order_id((rs.getInt("custom_order_id")));
//        customerOrders.setAccount_id(rs.getInt("account_id"));
//        customerOrders.setDelivery_id(rs.getInt("delivery_id"));
//        customerOrders.setNum_of_orders(rs.getInt("num_of_orders"));
//        customerOrders.setShipping(rs.getString("shipping"));
//        customerOrders.setStatus(rs.getString("status"));
//        return customerOrders;
//    }
//}