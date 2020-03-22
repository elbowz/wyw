package it.univaq.sose.user.business;

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

    public UserBusiness() {}

    public User getUserById(long id){
        try {
            Optional<User> optional = userRepository.findById(id);
            return optional.orElse(null);
        } catch (Exception e){
            logger.debug(e.getMessage());
        }
        return null;
    }

    public ArrayList<User> getAll() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

}
