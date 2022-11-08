package com.reactive.servicereactive.model.response;

import com.reactive.servicereactive.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCustomDocumentResponse extends BaseResponse {
    private Long id;
    private String code;
    private String fullname;
    private String phoneNumber;
    private String image;
    private String email;
    private String adress;
    private LocalDate dob;
    private List<CustomerDocumentResponse> customerDocumentResponses;
}
