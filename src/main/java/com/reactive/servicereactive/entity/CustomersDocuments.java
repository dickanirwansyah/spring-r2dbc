package com.reactive.servicereactive.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import com.reactive.servicereactive.base.BaseResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDocuments extends BaseResponse{

    @Id
    private Long id;
    private String code;
    private String customerCode;
    private String documentName;
    private String documentType;
    private String documentFile;
}
