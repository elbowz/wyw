package it.univaq.sose.user.service;

import io.swagger.annotations.Api;
import it.univaq.sose.user.exceptions.DuplicatedUserException;
import it.univaq.sose.user.business.UserBusiness;
import it.univaq.sose.user.model.User;
import org.apache.cxf.ext.logging.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Logging
@Service
@Api("/user")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    UserBusiness userBusiness;

    @Override
    public String pong() {
        return "Pong";
    }

    @Override
    public List<User> getAllUsers() {
        return userBusiness.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userBusiness.one(id);
    }

    @Override
    public User insertUser(@Valid User user) throws DuplicatedUserException {
        return userBusiness.save(user);
    }

    @Override
    public User updateUser(@Valid User newUser, long id) {
        return userBusiness.updateUser(id, newUser);
    }
}
