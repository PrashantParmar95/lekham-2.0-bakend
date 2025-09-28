package com.lekham.repositories;

import com.lekham.entities.mongo.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content, String> {
}

