package com.datn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.datn.dtos.request.SizeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "sizes")
public class Size extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "code", nullable = false, length = 50)
    private String code;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

    public static Size from(SizeRequest sizeRequest){
        return Size.builder()
                .name(sizeRequest.getName())
                .code(sizeRequest.getCode())
                .active(true)
                .build();
    }
}
