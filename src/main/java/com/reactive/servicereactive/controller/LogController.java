package com.reactive.servicereactive.controller;

import com.reactive.servicereactive.model.request.AuditLogRequest;
import com.reactive.servicereactive.model.response.AuditLogResponse;
import com.reactive.servicereactive.service.AuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping(value = "/api/v1/audit")
@RestController
public class LogController {

    private AuditLogService auditLogService;
    public LogController(AuditLogService auditLogService){
        this.auditLogService = auditLogService;
    }

    @PostMapping(value = "/trf-log")
    public ResponseEntity<Mono<AuditLogResponse>> trfLog(@RequestBody AuditLogRequest request){
        log.info("log request..");
        return ResponseEntity.ok(this.auditLogService.execute(request));
    }
}
