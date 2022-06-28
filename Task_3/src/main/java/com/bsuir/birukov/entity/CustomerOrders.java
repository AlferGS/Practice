package com.bsuir.birukov.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity                            //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Customer_orders")   //Указывает на таблицу, которая будет отображаться в этой сущности.
public class CustomerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "custom_order_id")
    private int custom_order_id;

    @ManyToMany
    @JoinTable(
            name = "customer_orders_has_order",
            joinColumns = @JoinColumn(name = "custom_order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    Set<Order> orderIDs;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "delivery_id")
    private int delivery_id;

    @Column(name = "num_of_orders")
    private int num_of_orders;

    @Column(name = "shipping")
    private String shipping;

    @Column(name = "status")
    private String status;

    public CustomerOrders(Object object){
        List<String> valuesName = new ArrayList<>() {
            {
                add("custom_order_id");
                add("account_id");
                add("delivery_id");
                add("num_of_orders");
                add("shipping");
                add("status");
            } };

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;    //+ lenght and =
            else idxValueDevider = object.toString().indexOf("}", x);
            String substring = object.toString().substring(idxValue, idxValueDevider);

            if(k == 0) this.custom_order_id =  Integer.parseInt(substring);
            if(k == 1) this.account_id =  Integer.parseInt(substring);
            if(k == 2) this.delivery_id =  Integer.parseInt(substring);
            if(k == 3) this.num_of_orders =  Integer.parseInt(substring);
            if(k == 4) this.shipping =  substring;
            if(k == 5) this.status =  substring;
            x = idxValueDevider + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerOrders that = (CustomerOrders) o;
        return custom_order_id != 0 && Objects.equals(custom_order_id, that.custom_order_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CustomerOrders [custom_order_id=" + custom_order_id +
                ", account_id=" + account_id +
                ", delivery_id=" + delivery_id +
                ", num_of_orders=" + num_of_orders +
                ", shipping=" + shipping +
                ", status=" + status + "]";
    }

    public boolean notNull(){
        if(this.custom_order_id <= 0)
            return  false;
        if(this.account_id <= 0)
            return false;
        if(this.delivery_id <= 0)
            return false;
        if(this.num_of_orders < 0)
            return false;
        if(this.shipping.isEmpty())
            return false;
        if(this.status.isEmpty())
            return false;
        return true;
    }
}
