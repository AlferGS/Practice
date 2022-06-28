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
@Entity                         //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Category")       //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int category_id;

    @ManyToMany(mappedBy = "categoryIDs")
    Set<Product> productIDs;

    @Column(name = "name")
    private String name;

    @Column(name = "num_of_categories")
    private int num_of_categories;

    @Column(name = "descriptions")
    private String descriptions;

    // Without column descriptions
    public Category(int category_id, String name, int num_of_categories){
        this.category_id = category_id;
        this.name = name;
        this.num_of_categories = num_of_categories;
    }

    public Category(Object object){
        System.out.println("Category constructor start");
        List<String> valuesName = new ArrayList<>() {
            {
                add("category_id");
                add("name");
                add("num_of_categories");
                add("descriptions");
            } };
        System.out.println("List created");

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            System.out.println("k = " + k + "\tx = " + x);
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;    //+ lenght and =
            else idxValueDevider = object.toString().indexOf("}", x) - 1;
            System.out.println("idxValue = " + idxValue + "\tidxValueDevider = " + idxValueDevider);
            String substring = object.toString().substring(idxValue, idxValueDevider);
            System.out.println("substring = " + substring);

            if(k == 0) this.category_id = Integer.parseInt(substring);
            if(k == 1) this.name = substring;
            if(k == 2) this.num_of_categories = Integer.parseInt(substring);
            if(k == 3) this.descriptions = substring;
            x = idxValueDevider + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return category_id != 0 && Objects.equals(category_id, category.category_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Category [category_id=" + category_id +
                ", name=" + name +
                ", num_of_categories=" + num_of_categories +
                ", descriptions=" + descriptions + "]";
    }

    public boolean notNull(){
        if(this.category_id <= 0)
            return  false;
        if(this.name.isEmpty())
            return false;
        if(this.num_of_categories <= 0)
            return false;
        return true;
    }

//    public Set<Product> delProductIDsById(Set<Product> productIDs,Integer id){
//        for(int i = 0; i< productIDs.size(); i++){
//            productIDs.removeIf(product_id->category_id==id);
//        }
//        return productIDs;
//    }
}
