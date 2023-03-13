package org.shruthipattanasetty.foodbankdonation.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Length(min = 2,max = 50, message = "Minimum length is 2 and maximum length ")
    @NonNull
    @Column(length = 48)
    String addressone;
    @NonNull
    @Column(length = 48)
    String city;
    @NonNull
    @Column(length = 48)
    String state;
    @NonNull
    @Column(length = 48)
    String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressone.equals(address.addressone) && city.equals(address.city) && state.equals(address.state) && zipcode.equals(address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressone, city, state, zipcode);
    }
}
