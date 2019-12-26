package com.abhilash.backend.restexample.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*THE SERVICE TO ACCESS THE DATABASE*/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
