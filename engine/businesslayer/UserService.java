package engine.businesslayer;

import engine.model.User;
import engine.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User userToSave) {
        return userRepository.save(userToSave);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findById(email);
    }

}
