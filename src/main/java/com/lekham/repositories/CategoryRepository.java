package com.lekham.repositories;

import com.lekham.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


    @Query(value="select count(id) from category where name =:name and created_by = :id",nativeQuery=true)
    public int getCountByNameAndUserId(@Param("name") String name,@Param("id") long userId);

    @Query(value="select * from category where created_by = :id",nativeQuery=true)
    List<Category> findByCreatedBy(@Param("id") long userId);
}
