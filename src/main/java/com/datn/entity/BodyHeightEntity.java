package com.datn.entity;

import com.datn.utils.constants.enums.BodyCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "body_height")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyHeightEntity {
    @Id
    private String id;

    @Column(name = "min_height")
    private Long minHeight;

    @Column(name = "max_height")
    private Long maxHeight;

    @Enumerated(EnumType.STRING)
    private BodyCodeEnum code;
}
