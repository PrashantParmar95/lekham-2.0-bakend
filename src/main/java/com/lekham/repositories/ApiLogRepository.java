package com.lekham.repositories;
import com.lekham.entities.mongo.ApiLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends MongoRepository<ApiLog, String> {
}