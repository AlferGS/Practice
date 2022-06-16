package main.java.com.bsuir.birukov.entity;

import javax.naming.StringRefAddr;

public class Account {
    public int account_id;
    public String full_name;
    public String email;
    public String password;
    public String phone_number;
    public String address;

    public Account(int account_id, String full_name, String email, String password, String phone_number, String address){
        this.account_id = account_id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.address = address;
    }
}
