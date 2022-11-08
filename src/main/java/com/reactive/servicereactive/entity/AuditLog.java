package com.reactive.servicereactive.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.reactive.servicereactive.base.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog extends BaseResponse{

    @Id
    private Long id;
    private String code;
    private LocalDateTime activityTime;
    private String remarks;
    private String currentThread;
}
