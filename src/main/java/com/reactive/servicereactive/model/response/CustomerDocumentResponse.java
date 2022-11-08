package com.reactive.servicereactive.model.response;


import java.util.List;

import com.reactive.servicereactive.base.BaseResponse;
import com.reactive.servicereactive.entity.CustomersDocuments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDocumentResponse extends BaseResponse{
    private List<CustomersDocuments> customerDocuments;
}
