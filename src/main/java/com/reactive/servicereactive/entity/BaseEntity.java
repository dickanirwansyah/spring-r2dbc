package com.reactive.servicereactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private String createdBy;
    private String updatedBy;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
