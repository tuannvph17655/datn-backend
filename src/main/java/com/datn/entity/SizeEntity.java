package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeEntity {

    @Id
    private String id;

    private String name;

    private String code;
}
