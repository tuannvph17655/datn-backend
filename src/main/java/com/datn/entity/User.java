package com.datn.entity;

import com.datn.dtos.request.UserRequest;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active;

    public static User copy(UserRequest src){
        User target = User.builder().build();
        CopyUtils.copy(src,target);
        return target.setRole(Role.valueOf(src.getRole()));
    }

}
