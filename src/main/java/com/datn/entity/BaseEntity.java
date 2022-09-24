package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity {

    @CreatedDate
    private Date created;

    @CreatedBy
    private String createBy;

    @LastModifiedDate
    private Date updated;

    @LastModifiedBy
    private String updatedBy;
}
