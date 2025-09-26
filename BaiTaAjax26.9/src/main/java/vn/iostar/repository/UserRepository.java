package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}