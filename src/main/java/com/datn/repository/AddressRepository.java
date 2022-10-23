package com.datn.repository;

import com.datn.dto.customer.address.AddressRes;
import com.datn.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    @Query("select new com.datn.dto.customer.address.AddressRes(" +
            "a.id,\n" +
            "a.nameOfRecipient,\n" +
            "a.addressDetail,\n" +
            "a.provinceId,\n" +
            "a.provinceName,\n" +
            "a.districtId,\n" +
            "a.districtName,\n" +
            "a.wardCode,\n" +
            "a.wardName,\n" +
            "trim(concat(coalesce(a.addressDetail, ''), ', ', coalesce(a.wardName, ''),', ', coalesce(a.districtName, ''),', ', coalesce(a.provinceName, ''))),\n" +
            "a.phoneNumber,\n" +
            "a.userId,\n" +
            "a.isDefault,\n" +
            "a.active)\n" +
            "from AddressEntity a\n" +
            "JOIN UserEntity u on a.userId = u.id\n" +
            "where u.id = :userId\n" +
            "ORDER BY a.isDefault DESC")
    List<AddressRes> getListAddressByUserId(@Param("userId") String userId);
    @Query(value = "SELECT * FROM address a WHERE a.user_id = ?1",nativeQuery = true)
    List<AddressEntity> getAddressByUserId(String userId);

    @Query("SELECT o FROM AddressEntity o WHERE o.userId = ?1 and o.isDefault = true ")
     AddressEntity getAddressDefaultByUserId(String userId);
}
