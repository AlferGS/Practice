//package com.bsuir.birukov.mappers;
//
//import com.bsuir.birukov.entity.Account;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class AccountRowMapper implements RowMapper<Account> {
//
//    public Account mapRow(ResultSet rs, int rowNum) throws SQLException{
//        Account account = new Account();
//        account.setAccount_id(rs.getInt("account_id"));
//        account.setFull_name(rs.getString("full_name"));
//        account.setEmail(rs.getString("email"));
//        account.setPassword(rs.getString("password"));
//        account.setPhone_number(rs.getString("phone_number"));
//        account.setAddress((rs.getString("address")));
//        return account;
//    }
//}
