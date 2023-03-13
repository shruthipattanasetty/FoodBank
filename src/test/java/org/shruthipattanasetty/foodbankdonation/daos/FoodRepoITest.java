package org.shruthipattanasetty.foodbankdonation.daos;

import org.junit.jupiter.api.Test;
import org.shruthipattanasetty.foodbankdonation.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FoodRepoITest {

    FoodRepoI foodRepoI;

    String zipcode = "48039";


    @Autowired
    public FoodRepoITest(FoodRepoI foodRepoI) {
        this.foodRepoI = foodRepoI;
    }


    @Test
    void  findByZipcode() {
        assertThat(foodRepoI.findByZipcode(zipcode).get(0).getZipcode()).isEqualTo("48039");
    }
}
