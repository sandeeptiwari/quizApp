package in.co.techiesandeep.service;

import in.co.techiesandeep.exception.HTTP404Exception;
import in.co.techiesandeep.exception.UnauthorizedActionException;
import in.co.techiesandeep.exception.UserAlreadyExistsException;
import in.co.techiesandeep.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    User findByUsername(String username);

    List<User> findAllUsers();

    Long numberOfUsers();

}
