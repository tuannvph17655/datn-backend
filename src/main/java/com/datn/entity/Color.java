package com.datn.entity;

import com.datn.dtos.request.ColorRequest;
import com.datn.dtos.request.UserRequest;
import com.datn.enums.Role;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.datn.dtos.request.ColorRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "colors")
public class Color extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "hex", nullable = false, length = 255)
    private String hex;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

    public static Color from(ColorRequest colorRequest){
        return Color.builder()
                .name(colorRequest.getName())
                .active(true)
                .build();

    }


}
