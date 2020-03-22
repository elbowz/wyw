package it.univaq.sose.user.service;

import io.swagger.annotations.Api;
import it.univaq.sose.user.business.UserBusiness;
import it.univaq.sose.user.model.User;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Logging
@Service
@Api("/user")
public class UserServiceImpl implements UserService {
    @Autowired
    UserBusiness userBusiness;

    @Override
    public String pong() {
        return "Pong";
    }

    @Override
    public List<User> getAllUsers() {
        return userBusiness.getAll();
    }

    @Override
    public User getUserById(Long id) {
        return userBusiness.getUserById(id);
    }
}
