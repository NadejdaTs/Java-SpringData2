package test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.models.Account;
import test.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //User findByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
