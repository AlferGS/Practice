package com.bsuir.birukov.entity;

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
public class Category {
    @Id
    private int category_id;
    private String name;
    private int num_of_category;
    private String descriptions;

    // Without column descriptions
    public Category(int category_id, String name, int num_of_category){
        this.category_id = category_id;
        this.name = name;
        this.num_of_category = num_of_category;
    }
}
