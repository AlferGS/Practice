//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.Order;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class OrderRowMapper implements RowMapper<Order> {
//
//    public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
//        Order order = new Order();
//        order.setOrder_id(rs.getInt("order_id"));
//        order.setProduct_id(rs.getInt("product_id"));
//        order.setName(rs.getString("name"));
//        order.setDate_time(rs.getDate("date_time"));
//        order.setNotes(rs.getString("notes"));
//        order.setQuantity(rs.getInt("quantity"));
//        order.setPrice(rs.getFloat("price"));
//        return order;
//    }
//}