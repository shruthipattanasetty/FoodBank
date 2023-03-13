package org.shruthipattanasetty.foodbankdonation.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@Slf4j

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @Column(length = 48,name="first_name")
    private String firstName;
    @Length(min = 2,max = 50,message = "name should have at least 2 characters")
    @Column(length = 48,name="last_name")
    private String lastName;
    @Column(length = 48)
    private String email;
    @Column(length = 48)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 48)
    private UserType userType;
   @OneToMany
    private List<Food> foods;

   @OneToOne
   private Address address;
    @Column(length = 48)
   private String zipcode;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password);
    }
}
