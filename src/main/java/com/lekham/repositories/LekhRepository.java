package com.lekham.repositories;

import com.lekham.entities.Lekh;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LekhRepository extends CrudRepository<Lekh, Long> {

    @Query(value = "SELECT * FROM lekh WHERE  deleted = 0 and created_by = :userId and category_id = :categoryId", nativeQuery = true)
    List<Lekh> findByUserIdandCategoryId(@Param("userId") long userId, @Param("categoryId") long categoryId);
}
