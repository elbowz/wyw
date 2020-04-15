package it.univaq.sose.user.business;

import it.univaq.sose.user.exceptions.DuplicatedUserException;
import it.univaq.sose.user.exceptions.UserNotFoundException;
import it.univaq.sose.user.model.User;
import it.univaq.sose.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserBusiness {
    private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    UserRepository userRepository;

    public UserBusiness() {
    }

    public User one(long id) throws UserNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseThrow(UserNotFoundException::new);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> optional = userRepository.findUserByEmail(email);
        return optional.orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) throws DuplicatedUserException {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new DuplicatedUserException();
        } else {
            return userRepository.save(user);
        }
    }

    // Do we need this?
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(long id, User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }
}