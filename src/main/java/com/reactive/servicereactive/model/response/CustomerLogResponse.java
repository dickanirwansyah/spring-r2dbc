package com.reactive.servicereactive.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reactive.servicereactive.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerLogResponse extends BaseResponse {
    private Long id;
    private String code;
    private String fullname;
    private String phoneNumber;
    private String image;
    private String email;
    private String adress;
    private LocalDate dob;
    private String remarks;
}
