package org.shruthipattanasetty.foodbankdonation.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepoITest {

    UserRepoI userRepoI;

    String email = "suja@gmail.com";

    @Autowired
    public UserRepoITest(UserRepoI userRepoI) {
        this.userRepoI = userRepoI;
    }

    @Test
    void findByEmail() {
        assertThat(userRepoI.findByEmail(email).get().getEmail()).isEqualTo("suja@gmail.com");
    }

}
