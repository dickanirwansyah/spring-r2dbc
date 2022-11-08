package com.reactive.servicereactive.model.request;

import com.reactive.servicereactive.base.BaseRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogRequest extends BaseRequest{
    
    private String remarks;
}
