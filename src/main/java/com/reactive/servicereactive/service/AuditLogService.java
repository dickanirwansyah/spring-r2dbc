package com.reactive.servicereactive.service;

import java.time.LocalDateTime;
import java.util.UUID;
import com.reactive.servicereactive.entity.AuditLog;
import com.reactive.servicereactive.repository.AuditLogRepository;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSON;
import com.reactive.servicereactive.base.BaseService;
import com.reactive.servicereactive.model.request.AuditLogRequest;
import com.reactive.servicereactive.model.response.AuditLogResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AuditLogService implements BaseService<AuditLogResponse, AuditLogRequest>{

    private AuditLogRepository auditLogRepository;
    public AuditLogService(AuditLogRepository auditLogRepository){
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public Mono<AuditLogResponse> execute(AuditLogRequest request) {
        log.info("request log -> {} ",JSON.toJSONString(request));
        return this.newLog(request)
                .map(auditLog -> AuditLogResponse.builder()
                        .remarks(auditLog.getRemarks())
                        .build());
    }

    private Mono<AuditLog> newLog(AuditLogRequest request){
        AuditLog auditLog = AuditLog.builder()
                .code(UUID.randomUUID().toString())
                .activityTime(LocalDateTime.now())
                .currentThread(Thread.currentThread().getName())
                .remarks(request.getRemarks())
                .build();
        return this.auditLogRepository.save(auditLog);
    }
}
