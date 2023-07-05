package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends GenericRepository<Manager> {

    Manager findManagerByLogin(String login);

    @Query(nativeQuery = true,
    value = "select user_id" +
            "    from users u\n" +
            "    inner join credit_contracts on credit_contracts.user_id = u.id\n" +
            "    where manager_id = :id")
    List<Integer> findUserIDSByManagerId(int id);

}
