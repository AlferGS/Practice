package main.java.com.bsuir.birukov.entity;

import java.sql.Date;

public class Order {
    public int order_id;
    public int product_id;
    private String name;
    private Date date_time;
    private String notes;
    private int quantity;
    private float price;
}
