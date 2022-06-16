package com.bsuir.birukov.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bsuir.birukov.repository.*;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.IsNewStrategy;

import javax.persistence.*;

import javax.naming.StringRefAddr;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity                 //Аннотация Entity устанавливает сущность для работы с БД
@Table                  //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Order {
    @Id
    private int order_id;
    private int product_id;
    private String name;
    private Date date_time;
    private String notes;
    private int quantity;
    private float price;

    // Without column notes
    public Order(int order_id, int product_id, String name, Date date_time, int quantity, float price){
        this.order_id = order_id;
        this.product_id = product_id;
        this.name = name;
        this.date_time = date_time;
        this.quantity = quantity;
        this.price = price;
    }
}
