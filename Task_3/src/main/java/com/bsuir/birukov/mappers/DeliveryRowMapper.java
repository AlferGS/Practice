//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.Delivery;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DeliveryRowMapper implements RowMapper<Delivery> {
//
//    public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException{
//        Delivery delivery = new Delivery();
//        delivery.setDelivery_id(rs.getInt("delivery_id"));
//        delivery.setName(rs.getString("name"));
//        delivery.setTime_of_order(rs.getDate("time_of_order"));
//        delivery.setPrice(rs.getFloat("price"));
//        delivery.setStatus(rs.getString("status"));
//        return delivery;
//    }
//}