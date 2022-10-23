package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "discount")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class DiscountEntity {
    @Id
    private String id;

    @Column(length = 500)
    private String des;

    private String code;

    /**
     * Ngày bắt đầu
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * Ngày kết thúc
     */
    @Column(name = "end_date")
    private Date endDate;
    /**
     * Giá trị để thỏa màn đièu kiện áp dụng khuyến mãi
     * Tổng giá trị đơn hàng tối thiệu / Số lượng sản phẩm tối thiểu
     */
    @Column(name = "prerequisite_value")
    private Long prerequisiteValue;

    /**
     * trạng thái
     * Ngung áp dụng/ đang áp dụng / chua ap dung
     */

    /*
    * phần trăm giảm giá*/
    private Long percentDiscount;

    private String status;

    private Boolean deleted;

    private Long eventId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
