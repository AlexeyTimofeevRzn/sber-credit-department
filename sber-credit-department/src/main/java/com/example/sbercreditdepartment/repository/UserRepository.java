package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User findUserByLogin(String login);

}
