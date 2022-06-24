package com.bsuir.birukov.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity                        //Аннотация Entity устанавливает сущность для работы с БД
@Table(name = "Account")       //Указывает на таблицу, которая будет отображаться в этой сущности.
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int account_id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    public Account(Object object){
        List<String> valuesName = new ArrayList<>() {
            {
                add("account_id");
                add("full_name");
                add("email");
                add("password");
                add("phone_number");
                add("address");
            } };

        for(int k = 0, x = 0; k < valuesName.size(); k++){
            System.out.println("k = " + k + "\tx = " + x);
            int idxValue = object.toString().indexOf("=", x) + 1;
            int idxValueDevider;
            if(k != valuesName.size()-1)
                idxValueDevider = object.toString().indexOf(valuesName.get(k+1)) - 2;
            else idxValueDevider = object.toString().indexOf("}", x);
            System.out.println("idxValue = " + idxValue + "\tidxValueDevider = " + idxValueDevider);
            String substring = object.toString().substring(idxValue, idxValueDevider);
            System.out.println("substring = " + substring);

            if(k == 0) this.account_id =  Integer.parseInt(substring);
            if(k == 1) this.full_name =  substring;
            if(k == 2) this.email =  substring;
            if(k == 3) this.password = substring;
            if(k == 4) this.phone_number =  substring;
            if(k == 5) this.address =  substring;
            x = idxValueDevider + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return account_id != 0 && Objects.equals(account_id, account.account_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Account [account_id=" + account_id +
                            ", name=" + full_name +
                            ", email=" + email +
                            ", password=" + password +
                            ", ph_number=" + phone_number +
                            ", address=" + address +"]";
    }

    public boolean notNull(){
        if(this.account_id <= 0)
            return  false;
        if(this.full_name.isEmpty())
            return false;
        if(this.email.isEmpty())
            return false;
        if(this.password.isEmpty())
            return false;
        if(this.phone_number.isEmpty())
            return false;
        if(this.address.isEmpty())
            return false;
        return true;
    }
}
