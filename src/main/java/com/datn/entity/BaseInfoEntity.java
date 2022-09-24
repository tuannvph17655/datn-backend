package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInfoEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String numberPhone;
    private String cityAddress;
    private boolean genderType;

}
