package com.reactive.servicereactive.model.response;

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
public class CustomerResponse extends BaseResponse {
    private Long customerId;
    private String customerCode;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String address;
    private String phoneNumber;
}
