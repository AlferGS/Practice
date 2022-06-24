package com.bsuir.birukov.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity                     //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Delivery")   //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Delivery {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "delivery_id")
    private int delivery_id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_of_order")
    private Date time_of_order;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private String status;

    public Delivery(Object object) throws SQLException, ParseException {
        List<String> valuesName = new ArrayList<>() {
            {
                add("delivery_id");
                add("name");
                add("time_of_order");
                add("price");
                add("status");
            } };

        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        ResultSet rs = null;

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;    //+ lenght and =
            else idxValueDevider = object.toString().indexOf("}", x);
            String substring = object.toString().substring(idxValue, idxValueDevider);

            if(k == 0) this.delivery_id = Integer.parseInt(substring);
            if(k == 1) this.name = substring;
            if(k == 2) {
                assert false;
                this.time_of_order = (Date) formatter.parse(String.valueOf(rs.getDate(substring)));
            }
            if(k == 3) this.price = Float.parseFloat(substring);
            if(k == 4) this.status = substring;
            x = idxValueDevider + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return delivery_id != 0 && Objects.equals(delivery_id, delivery.delivery_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Delivery [delivery_id=" + delivery_id +
                ", name=" + name +
                ", time_of_order=" + time_of_order +
                ", price=" + price +
                ", status=" + status + "]";
    }

    public boolean notNull(){
        if(this.delivery_id <= 0)
            return  false;
        if(this.name.isEmpty())
            return false;
        if(this.price <= 0)
            return false;
        if(this.status.isEmpty())
            return false;
        return true;
    }
}
