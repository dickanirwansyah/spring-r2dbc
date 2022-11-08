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
public class CustomerRequest extends BaseRequest {

    private Long customerId;
    private String customerCode;
    private String customerFullName;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerAddress;
    private String customerDob;
}
