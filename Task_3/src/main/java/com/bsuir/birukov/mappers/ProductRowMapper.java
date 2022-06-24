//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.Product;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ProductRowMapper implements RowMapper<Product> {
//
//    public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
//        Product product = new Product();
//        product.setProduct_id(rs.getInt("product_id"));
//        product.setName(rs.getString("name"));
//        product.setManufactorer(rs.getString("manufactorer"));
//        product.setWidth(rs.getFloat("width"));
//        product.setHeight(rs.getFloat("height"));
//        product.setWeight(rs.getFloat("weight")); ;
//        product.setPrice(rs.getFloat("price"));
//        return product;
//    }
//}