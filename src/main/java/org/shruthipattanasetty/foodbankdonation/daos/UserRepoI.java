package org.shruthipattanasetty.foodbankdonation.daos;

import org.shruthipattanasetty.foodbankdonation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoI extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email,String password);
}
