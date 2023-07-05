package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.Credit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends GenericRepository<Credit> {

    @Query(nativeQuery = true,
            value = "select * from credit_types where is_deleted = false")
    List<Credit> findCreditsNotDeleted();

}
