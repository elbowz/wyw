package it.univaq.sose.watched_film.business;


import feign.hystrix.FallbackFactory;
import it.univaq.sose.watched_film.client.UserServiceClient;
import it.univaq.sose.watched_film.model.User;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class UserFallbackFactory implements FallbackFactory<UserServiceClient> {
    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceClient() {
            @Override
            public CompletableFuture<User> getUserById(long userId) {
                // When user-ws is down return null.
                return CompletableFuture.completedFuture(null);
            }
        };
    }
}