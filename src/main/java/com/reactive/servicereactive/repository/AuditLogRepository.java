package com.reactive.servicereactive.repository;

import com.reactive.servicereactive.entity.AuditLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends ReactiveCrudRepository<AuditLog, String> {
}
