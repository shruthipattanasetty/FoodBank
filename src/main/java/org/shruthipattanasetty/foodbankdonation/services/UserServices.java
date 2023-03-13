package org.shruthipattanasetty.foodbankdonation.services;

import jakarta.transaction.Transactional;
import org.shruthipattanasetty.foodbankdonation.daos.UserRepoI;
import org.shruthipattanasetty.foodbankdonation.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServices implements UserService {

    @Autowired
    UserRepoI userRepoI;

    @Override
    public List<User> getUsers() {
        return userRepoI.findAll();
    }

    @Override
    public void saveUser(User user){
        userRepoI.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password){
        return userRepoI.findByEmailAndPassword(email,password);
    }

    @Override
    public User getUserById(int id) throws Exception {
        Optional<User> user = userRepoI.findById(id);
        User user1;
        if (user.isPresent()) {
            user1 = user.get();
        }else {
            throw new Exception("User not found");
        }
        return user1;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepoI.findByEmail(email);
        if (user.isPresent()){
            return user.get();
        }else {
            return null;
        }
    }

}
