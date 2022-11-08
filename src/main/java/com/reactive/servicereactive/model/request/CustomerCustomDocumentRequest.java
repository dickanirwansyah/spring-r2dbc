package com.reactive.servicereactive.model.request;

import com.reactive.servicereactive.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCustomDocumentRequest extends BaseRequest {
    private String customerFullName;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerAddress;
    private String customerDob;
    private List<CustomerDocumentRequest> customerDocumentRequests;
}
