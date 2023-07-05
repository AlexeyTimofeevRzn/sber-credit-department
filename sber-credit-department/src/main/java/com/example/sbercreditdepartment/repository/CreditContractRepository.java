package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.CreditContract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.Date;

@Repository
public interface CreditContractRepository extends GenericRepository<CreditContract> {

    @Query(nativeQuery = true,
            value = "select * from credit_contracts where start_date between :start and :end")
    List<CreditContract> getCreditContractBetweenTwoDates(Date start, Date end);

}
