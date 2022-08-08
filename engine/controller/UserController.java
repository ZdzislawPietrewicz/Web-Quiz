package engine.controller;

import engine.businesslayer.UserService;
import engine.errors.EmailNotCorrectOrPasswordToShort;
import engine.errors.QuizNotExist;
import engine.errors.UserAlreadyExist;
import engine.model.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public void addNewUser(@RequestBody User user) {
        String passwordRegex = "\\s*[a-zA-Z0-9_$]{5,12}\\s*";
        String emailRegex = "^(.+)@(.+)$";
        if (user.getEmail().matches(emailRegex)) {git
            userService.findUserByEmail(user.getEmail())
                    .ifPresentOrElse((userExist) -> new QuizNotExist(userExist.getEmail() + " - email already exist"),
                            () -> userService.save(user));
        } else {
            throw new EmailNotCorrectOrPasswordToShort("Email has not good format or password is too short.");
        }
    }
}
