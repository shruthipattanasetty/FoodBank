package org.shruthipattanasetty.foodbankdonation.secuirty;


import org.shruthipattanasetty.foodbankdonation.daos.AuthGroupRepoI;
import org.shruthipattanasetty.foodbankdonation.daos.UserRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service

public class MyUserDetailsService implements UserDetailsService {


    UserRepoI myUserRepoI;
    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public MyUserDetailsService(UserRepoI myUserRepoI, AuthGroupRepoI authGroupRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserPrincipal
                (myUserRepoI.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username/password" + username))
                        , authGroupRepoI.findByEmail(username));
    }
}
