//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.Category;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CategoryRowMapper implements RowMapper<Category> {
//
//    public Category mapRow(ResultSet rs, int rowNum) throws SQLException{
//        Category category = new Category();
//        category.setCategory_id(rs.getInt("category_id"));
//        category.setName(rs.getString("name"));
//        category.setNum_of_categories(rs.getInt("num_of_categories"));
//        category.setDescriptions((rs.getString("descriptions")));
//        return category;
//    }
//}