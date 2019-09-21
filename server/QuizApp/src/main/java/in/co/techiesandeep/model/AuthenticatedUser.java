package in.co.techiesandeep.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthenticatedUser{

    private static final long serialVersionUID = 1L;

    private User user;

    public AuthenticatedUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }



    public Long getId() {
        return user.getId();
    }



}
