package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User findUserByLogin(String login);
    User findUserByEmail(String email);

}
