package org.shruthipattanasetty.foodbankdonation.daos;

import org.shruthipattanasetty.foodbankdonation.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodRepoI extends JpaRepository<Food,Integer> {

    List<Food> findByZipcode(String zipcode);
}
