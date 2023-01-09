package com.datn.entity;

import com.datn.utils.constants.enums.VoucherStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "voucher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class VoucherEntity {
    @Id
    private String id;
    @Column(length = 500)
    private String des;
    private String code;

    /**
     * Ngày bắt đầu
     */
    @Column(name = "start_date")
    private String startDate;

    /**
     * Ngày kết thúc
     */
    @Column(name = "end_date")
    private String endDate;
    /**
     * Giá trị để thỏa màn đièu kiện áp dụng khuyến mãi
     * Tổng giá trị đơn hàng tối thiểu
     */
    private Long prerequisiteValue;

    /**
     * trạng thái
     * Ngung áp dụng/ đang áp dụng / chua ap dung
     */
    @Enumerated(EnumType.STRING)
    private VoucherStatus status;

    /*
     * phần trăm giảm giá*/
    private Long percent;

    private Boolean active;

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
