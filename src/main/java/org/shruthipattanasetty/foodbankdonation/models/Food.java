package org.shruthipattanasetty.foodbankdonation.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Slf4j
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @Column(length = 48,name="name")
    private String name;

    @Range(min=1,message = "Minimum quantity should be 1")
    private int quantity;


    private String status;

    @CreationTimestamp
    private Date createdOn;
    private Date blockedOn;


    private String pickedBy;

    @Column(length = 48)
    private String zipcode;
    @Column(length = 48)
    private String address;
    @Column(length = 48)
    private int volunteerId;

//

//    public Food(String name, int quantity, String status, String zipcode) {
//        this.name = name;
//        this.quantity = quantity;
//        this.status = status;
//        this.zipcode = zipcode;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Food food = (Food) o;
//        return quantity == food.quantity && Objects.equals(name, food.name) && Objects.equals(status, food.status) && Objects.equals(zipcode, food.zipcode);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, status, zipcode);
    }
}
