package com.bsuir.birukov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bsuir.birukov.repository.*;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.support.IsNewStrategy;

import javax.persistence.*;

import java.sql.Date;
import javax.naming.StringRefAddr;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity                 //Аннотация Entity устанавливает сущность для работы с БД
@Table                  //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Delivery {
    @Id
    private int delivery_id;
    private String name;
    private Date time_od_order;
    private float price;
    private String status;
}
