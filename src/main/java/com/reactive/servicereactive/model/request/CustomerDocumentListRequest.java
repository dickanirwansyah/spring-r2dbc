package com.reactive.servicereactive.model.request;

import java.util.List;

import com.reactive.servicereactive.base.BaseRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDocumentListRequest extends BaseRequest{
    private List<CustomerDocumentRequest> customerDocumentList;
}
