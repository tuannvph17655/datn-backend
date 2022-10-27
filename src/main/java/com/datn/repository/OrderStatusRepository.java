package com.datn.repository;

import com.datn.dto.admin.detail.StatusDto;
import com.datn.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, String> {

    @Query("select new com.datn.dto.admin.detail.StatusDto(\n" +
            "os.status,\n" +
            "os.createdDate,\n" +
            "u.role,\n" +
            "concat(u.firstName, ' ', u.lastName))\n" +
            "from OrderStatusEntity os\n" +
            "left join UserEntity u on u.id = os.createdBy\n" +
            "where os.orderId = :orderId\n" +
            "order by os.createdDate")
    List<StatusDto> findHistory(@Param("orderId") String orderId);
}
