package it.univaq.sose.user.service;

import io.swagger.annotations.Api;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.stereotype.Service;

@Logging
@Service
@Api("/user")
public class UserServiceImpl implements UserService {
    @Override
    public String pong() {
        return "Pong";
    }
}
