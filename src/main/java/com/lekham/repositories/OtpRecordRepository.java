package com.lekham.repositories;

import com.lekham.entities.OtpRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRecordRepository extends CrudRepository<OtpRecord, Integer> {

    @Query(value = "SELECT otp FROM otp_record WHERE email = :email order by id desc limit 1", nativeQuery = true)
    String fetchLatestOTP(@Param("email") String email);
}
