package in.co.techiesandeep.controller;

import in.co.techiesandeep.jwt.JwtTokenProvider;
import in.co.techiesandeep.model.Role;
import in.co.techiesandeep.model.User;
import in.co.techiesandeep.service.UserService;
import in.co.techiesandeep.util.AppUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-1/
@RestController
@RequestMapping(AppUtilities.USER_ROOT_MAPPING)
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    private String test() {
        return "Welcome board";
    }

    @PostMapping(value = "/registration")
    //@PreAuthorize("permitAll")
    public ResponseEntity<User> signUp(@RequestBody User user) {
       // RestVerifier.verifyModelResult(result);

        if(userService.findByUsername(user.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //default role.
        user.setRole(Role.USER);

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getUser(Principal principal){
        //principal = httpServletRequest.getUserPrincipal.
        if(principal == null){
          //logout will also use here so we should return ok http status.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken)principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(tokenProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
