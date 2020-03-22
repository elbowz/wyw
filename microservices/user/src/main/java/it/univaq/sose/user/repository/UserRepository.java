package it.univaq.sose.user.repository;

import it.univaq.sose.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
