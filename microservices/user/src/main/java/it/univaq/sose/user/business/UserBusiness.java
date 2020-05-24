package it.univaq.sose.user.business;

import it.univaq.sose.user.exceptions.DuplicatedUserException;
import it.univaq.sose.user.exceptions.UserNotFoundException;
import it.univaq.sose.user.model.User;
import it.univaq.sose.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserBusiness {
    private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    EurekaInstanceConfigBean eurekaInstanceConfigBean;

    public UserBusiness() {
    }

    public User one(long id) throws UserNotFoundException {
        Optional<User> optional = userRepository.findById(id);

        optional.ifPresent(user -> user.setInstanceId(this.eurekaInstanceConfigBean.getInstanceId()));

        return optional.orElseThrow(UserNotFoundException::new);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list =(ArrayList<User>) this.userRepository.findAll();
        list.forEach(user -> user.setInstanceId(this.eurekaInstanceConfigBean.getInstanceId()));
        return list;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> optional = userRepository.findUserByEmail(email);

        optional.ifPresent(user -> user.setInstanceId(this.eurekaInstanceConfigBean.getInstanceId()));

        return optional.orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) throws DuplicatedUserException {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new DuplicatedUserException();
        } else {
            return userRepository.save(user);
        }
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

    public User login(String email, String password) throws UserNotFoundException {
        User user = this.getUserByEmail(email);

        if (user.getPassword() != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }
}
