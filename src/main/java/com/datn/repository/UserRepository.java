package com.datn.repository;

import com.datn.dto.customer.user.CustomerResponse;
import com.datn.dto.customer.user.UserDto;
import com.datn.entity.UserEntity;
import com.datn.utils.base.rest.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmailAndActive(String value, Boolean active);

    boolean existsByEmail(String email);

    UserEntity findByIdAndActive(String id, Boolean aTrue);

    boolean existsByPhone(String phone);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneAndIdNot(String phone, String id);

    boolean existsByIdAndActive(String id, boolean active);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, String id);

    @Query("select new com.datn.utils.base.rest. CurrentUser(\n" +
            "u.id,\n" +
            "trim(concat(coalesce(u.firstName, ''), ' ', coalesce(u.lastName, ''))),\n" +
            "u.role,\n" +
            "u.email)\n" +
            "from UserEntity u\n" +
            "where u.active = true and u.id = :id")
    CurrentUser findCurrentUserByIdAndActive(@Param("id") String id);


    @Query("select new com.datn.dto.customer.user.UserDto(" +
            "u.email,\n" +
            "u.password,\n" +
            "u.role)\n" +
            "from UserEntity u\n" +
            "where u.email = :email and u.active = :active")
    UserDto findUserDtoByEmail(@Param("email") String email, @Param("active") Boolean active);

    @Query("select new com.datn.dto.customer.user.CustomerResponse(\n" +
            "u.id,\n" +
            "trim(concat(coalesce(u.firstName, ''), ' ', coalesce(u.lastName, ''))),\n" +
            "u.role,\n" +
            "u.dob,\n" +
            "u.phone,\n" +
            "trim(concat(coalesce(a.addressDetail, ''), ', ', coalesce(a.wardName, ''),', ', coalesce(a.districtName, ''),', ', coalesce(a.provinceName, ''))),\n" +
            "u.email)\n" +
            "from UserEntity u\n" +
            "JOIN AddressEntity a on a.userId = u.id\n" +
            "where u.active = true and a.isDefault = true and u.id = :id")
    CustomerResponse findCustomerById(@Param("id") String id);

    @Query("select (count(u) > 0) from UserEntity u where upper(u.email) = upper(?1) and u.active = ?2")
    boolean existsByEmailIgnoreCaseAndActive(String email, boolean b);

    @Query("select (count(u) > 0) from UserEntity u where u.phone = ?1 and u.active = ?2")
    boolean existsByPhoneAndActive(String trim, boolean b);
}
