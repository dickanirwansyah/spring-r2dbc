package com.reactive.servicereactive.model.response;

import com.reactive.servicereactive.base.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogResponse extends BaseResponse{
    private String remarks;

}
