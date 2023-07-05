package com.example.sbercreditdepartment.repository;

import com.example.sbercreditdepartment.model.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RequestRepository extends GenericRepository<Request> {

    @Query(nativeQuery = true,
            value = "select * from requests where date_of_request between :start and :end")
    List<Request> requestsBetweenTwoDates(@Param("start") Date start, @Param("end") Date end);

    @Query(nativeQuery = true,
            value = "select * from requests where request_status != 'SIGNED' and user_id = :id")
    List<Request> findRequestByUserIdAndNotSigned(int id);

    @Query(nativeQuery = true,
            value = "select * from requests where request_status != 'SIGNED'")
    List<Request> findRequestNotSigned();

    List<Request> findRequestByUserId(int id);
}
