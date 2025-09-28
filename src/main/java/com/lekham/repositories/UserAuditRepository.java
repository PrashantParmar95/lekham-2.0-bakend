package com.lekham.repositories;

import com.lekham.entities.audit.UsersAudit;
import org.springframework.data.repository.CrudRepository;

public interface UserAuditRepository extends CrudRepository<UsersAudit,Long> {
}
