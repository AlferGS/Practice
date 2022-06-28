package com.bsuir.birukov.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity                   //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Order")    //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int order_id;

    @ManyToMany(mappedBy = "orderIDs")
    Set<CustomerOrders> custOrderIDs;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_time")
    private Date date_time;

    @Column(name = "notes")
    private String notes;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private float price;

    public Order(Object object) {
        System.out.println("Start Order(obj)");
        List<String> valuesName = new ArrayList<>() {
            {
                add("order_id");
                add("product_id");
                add("name");
                add("date_time");
                add("notes");
                add("quantity");
                add("price");
            } };

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;
            else idxValueDevider = object.toString().indexOf("}", x);
            String substring = object.toString().substring(idxValue, idxValueDevider);

            if(k == 0) this.order_id =  Integer.parseInt(substring);
            if(k == 1) this.product_id =  Integer.parseInt(substring);
            if(k == 2) this.name =  substring;
            if(k == 3) this.date_time = Date.valueOf(substring);
            if(k == 4) this.notes =  substring;
            if(k == 5) this.quantity =  Integer.parseInt(substring);
            if(k == 6) this.price =  Float.parseFloat(substring);
            x = idxValueDevider + 1;
        }
        System.out.println("End Order(obj)");
    }

    // Without column notes
    public Order(int order_id, int product_id, String name, Date date_time, int quantity, float price){
        this.order_id = order_id;
        this.product_id = product_id;
        this.name = name;
        this.date_time = date_time;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return order_id != 0 && Objects.equals(order_id, order.order_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Order [order_id=" + order_id +
                ", product_id=" + product_id +
                ", name=" + name +
                ", date_time=" + date_time +
                ", notes=" + notes +
                ", quantity=" + quantity +
                ", price=" + price + "]";
    }

    public boolean notNull(){
        System.out.println("Start notNull");
        if(this.order_id <= 0)
            return  false;
        if(this.product_id <= 0)
            return  false;
        if(this.name.isEmpty())
            return false;
        if(this.quantity <= 0)
            return false;
        if(this.price <= 0)
            return false;
        System.out.println("End notNull");
        return true;
    }
}
