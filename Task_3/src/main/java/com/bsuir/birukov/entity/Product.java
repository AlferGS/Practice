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
@Entity                     //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Product")    //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int product_id;

    @ManyToMany
    @JoinTable(
            name = "product_has_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> has_category;

    @Column(name = "name")
    private String name;

    @Column(name = "manufactorer")
    private String manufactorer;

    @Column(name = "width")
    private float width;

    @Column(name = "height")
    private float height;

    @Column(name = "weight")
    private float weight;

    @Column(name = "price")
    private float price;

    public Product(Object object){
        System.out.println("Product constructor start");
        List<String> valuesName = new ArrayList<>() {
            {
                add("product_id");
                add("name");
                add("manufactorer");
                add("width");
                add("height");
                add("weight");
                add("price");
            } };
        System.out.println("List created");

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            System.out.println("k = " + k + "\tx = " + x);
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;    //+ lenght and =
            else idxValueDevider = object.toString().indexOf("}", x);
            System.out.println("idxValue = " + idxValue + "\tidxValueDevider = " + idxValueDevider);
            String substring = object.toString().substring(idxValue, idxValueDevider);
            System.out.println("substring = " + substring);

            if(k == 0) this.product_id = Integer.parseInt(substring);
            if(k == 1) this.name = substring;
            if(k == 2) this.manufactorer = substring;
            if(k == 3) this.width = Float.parseFloat(substring);
            if(k == 4) this.height = Float.parseFloat(substring);
            if(k == 5) this.weight = Float.parseFloat(substring);
            if(k == 6) this.price = Float.parseFloat(substring);
            x = idxValueDevider + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return product_id != 0 && Objects.equals(product_id, product.product_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Product [product_id=" + product_id +
                ", name=" + name +
                ", manufactorer=" + manufactorer +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                ", price=" + price + "]";
    }

    public boolean notNull(){
        if(this.product_id <= 0)
            return  false;
        if(this.name.isEmpty())
            return false;
        if(this.manufactorer.isEmpty())
            return false;
        if(this.width <= 0)
            return false;
        if(this.height <= 0)
            return false;
        if(this.weight <= 0)
            return false;
        if(this.price <= 0)
            return false;
        return true;
    }
}