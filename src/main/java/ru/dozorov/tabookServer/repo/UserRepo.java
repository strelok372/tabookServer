package ru.dozorov.tabookServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dozorov.tabookServer.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
