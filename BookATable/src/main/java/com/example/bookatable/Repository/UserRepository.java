package com.example.bookatable.Repository;

import com.example.bookatable.Model.User;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById (Integer id);
    User findUserByUsername(String username);
}
