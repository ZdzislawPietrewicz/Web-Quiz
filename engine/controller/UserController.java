package engine.controller;

import engine.businesslayer.UserService;
import engine.errors.EmailNotCorrectOrPasswordToShort;
import engine.errors.QuizNotExist;
import engine.errors.UserAlreadyExist;
import engine.model.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public void addNewUser(@RequestBody User user) {
        String passwordRegex = "\\s*[a-zA-Z0-9_$]{5,}\\s*";
        String emailRegex = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
        if (user.getEmail().matches(emailRegex) && user.getPassword().matches(passwordRegex)) {
            if (userService.findUserByEmail(user.getEmail()).isPresent())
                throw new UserAlreadyExist("Email already exist");
            else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.save(user);
            }
        } else {
            throw new EmailNotCorrectOrPasswordToShort("Email has not good format or password is too short.");
        }
    }

    @GetMapping("api/showAllUsers")
    public Iterable<User> showAllUsers() {
        return userService.findAllAccounts();
    }

    @DeleteMapping("api/delete")
    public void deleteAllAccounts() {
        userService.deleteAllAccounts();
    }
    @GetMapping("api/user")
    public void showUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getAuthorities());
    }
}
