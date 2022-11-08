package com.reactive.servicereactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import com.reactive.servicereactive.base.BaseResponse;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customers extends BaseResponse{

    @Id
    private Long id;
    private String code;
    private String fullname;
    private String phoneNumber;
    private String image;
    private String email;
    private String adress;
    private LocalDate dob;
}
