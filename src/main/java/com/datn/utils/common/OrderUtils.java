package com.datn.utils.common;

import com.datn.dto.admin.detail.PriceDto;
import com.datn.dto.admin.detail.PromotionDto;
import com.datn.dto.admin.detail.PriceResult;
import com.datn.dto.admin.detail.StatusDto;
import com.datn.dto.admin.order.search.OptionDto;
import com.datn.entity.UserEntity;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;
import com.datn.utils.constants.enums.PromotionTypeEnum;
import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.constants.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
public class OrderUtils {

    private OrderUtils() {
    }

    /**
     * @param statusStr       trạng thái hiện tại
     * @param createdDate     thời gian tạo trạng thái hiện tại
     * @param combinationName tên người tạo ra trạng thái = firstName + lastName
     * @param roleStr         role người tạo ra trạng thái
     * @return vd: Đơn hàng đã được chấp nhận bởi Admin lúc 10:22:23 12/02/2022
     */
    public static String getStatusCombination(String statusStr, Date createdDate, String roleStr, String combinationName) {
        String result = "";
        StatusEnum status = StatusEnum.from(statusStr);
        String dateFmt = DateUtils.toStr(createdDate, DateUtils.F_DDMMYYYYHHMM);
        RoleEnum role = RoleEnum.valueOf(roleStr);

        switch (status) {
            case PENDING:
                result = "Đang chờ xứ lý";
                break;
            case CANCEL:
                result = "Đã bị hủy bới khách hàng vào lúc " + dateFmt;
                break;
            case REJECT:
                result = "Đã bị từ chối bởi " + role.getName() + " " + combinationName + " vào lúc " + dateFmt;
                break;
            case ACCEPT:
                result = "Được chấp nhận bởi " + role.getName() + " " + combinationName + " vào lúc " + dateFmt;
                break;
            default:
                break;
        }


        return result;
    }

    /**
     * @param shopPrice  Tổng tiền sản phẩm chưa trừ đi khuyến mãi sản phẩm.
     * @param shipPrice  Tiền ship
     * @param promotions Danh sách khuyến mãi
     * @return giá phải trả
     * @apiNote Dựa vào loại khuyến mãi và % giảm giá sẽ trừ đi vào giá
     */
    public static Long getTotal(Long shopPrice, Long shipPrice, PromotionDto promotion) {
        try {
            /**
             * Nếu không có khuyến mãi thì = defaultTotal + shipPrice
             * */
            if (Objects.nonNull(promotion)) {
                    PromotionTypeEnum type = PromotionTypeEnum.valueOf(promotion.getTypeCode());
                    switch (type) {
                        /**
                         * Loại 1: SHIP
                         * Trừ tiền ship
                         * */
                        case TYPE1:
                            shipPrice -= shipPrice * promotion.getPercentDiscount().longValue() / 100;
                            break;
                        /**
                         * Loại 2: Mua sắm
                         * Trừ tiền sản phẩm
                         * */
                        case TYPE2:
                            shopPrice -= shopPrice * promotion.getPercentDiscount().longValue() / 100;
                            break;
                        default:
                            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
                    }

            }
        } catch (Exception e) {
            log.error("getTotal: {}", e.getMessage());
        }
        return shopPrice + shipPrice;
    }

//    public static PriceResult getResultDto(long shopPrice, Long shipPrice, PromotionDto promotion) {
//        long shipDiscount = 0L;
//        long shopDiscount = 0l;
//
//        if (Objects.nonNull(promotion)) {
//                PromotionTypeEnum type = PromotionTypeEnum.valueOf(promotion.getTypeCode());
//                switch (type) {
//                    case TYPE1:
//                        shipDiscount += shipPrice * promotion.getPercentDiscount().longValue() / 100;
//                        break;
//                    case TYPE2:
//                        shopDiscount += shopPrice * promotion.getPercentDiscount().longValue() / 100;
//                        break;
//                    default:
//                        throw new PuddyException(PuddyCode.INTERNAL_SERVER);
//                }
//        }
//
//        long shipTotal = shipPrice - shipDiscount;
//        long shopTotal = shopPrice - shopDiscount;
//
//        long total = shipTotal + shopTotal;
//
//        return PriceResult.builder()
//                .ship(PriceDto.builder()
//                        .name("Vận chuyển")
//                        .price(MoneyUtils.format(shipPrice))
//                        .discount(MoneyUtils.format(shipDiscount))
//                        .total(MoneyUtils.format(shipTotal))
//                        .build())
//                .shop(PriceDto.builder()
//                        .name("Mua sắm")
//                        .price(MoneyUtils.format(shopPrice))
//                        .discount(MoneyUtils.format(shopDiscount))
//                        .total(MoneyUtils.format(shopTotal))
//                        .build())
//                .total(MoneyUtils.format(total))
//                .build();
//    }

    public static List<OptionDto> getOptions4Admin(String statusNow) {
        ArrayList<OptionDto> result = new ArrayList<>();
        try {
            StatusEnum status = StatusEnum.valueOf(statusNow);
            switch (status) {
                case PENDING:
                    result.add(OptionDto.builder()
                            .name("Chấp nhận đơn hàng")
                            .status(StatusEnum.ACCEPT.name().toLowerCase(Locale.ROOT))
                            .clazz("success")
                            .build());
                    result.add(OptionDto.builder()
                            .name("Hủy đơn hàng")
                            .status(StatusEnum.REJECT.name().toLowerCase(Locale.ROOT))
                            .clazz("danger")
                            .build());
                    break;
                case ACCEPT:
                    result.add(OptionDto.builder()
                            .name("Hủy đơn hàng")
                            .status(StatusEnum.REJECT.name().toLowerCase(Locale.ROOT))
                            .clazz("danger")
                            .build());
                    break;
                default:
                    return Collections.emptyList();
            }

        } catch (Exception e) {
            log.error("getOptions: {}", e.getMessage());
        }
        return result;
    }

    public static List<String> getHistory(List<StatusDto> orderStatusList) {
        List<String> result = new ArrayList<>();
        orderStatusList.forEach(obj -> {
            String item = DateUtils.toStr(obj.getCreatedDate(), DateUtils.F_DDMMYYYYHHMMSS) + ": ";
            String combination = obj.getRole().getName() + " " + obj.getFullName();
            switch (obj.getStatus()) {
                case PENDING:
                    item += "Đặt hàng thành công. Đang chờ xác nhận";
                    break;
                case ACCEPT:
                    item += "Được chấp nhận bỏi " + combination;
                    break;
                case REJECT:
                    item += "Bị từ chối bỏi " + combination;
                    break;
                case CANCEL:
                    item += "Khách hàng hủy đơn hàng";
                    break;
                case SHIPPING:
                    item += "Đơn hàng đang được giao !";
                    break;
                case RECEIVED :
                    item += "Đơn hàng được giao thành công !";
                    break;
                case UNRECEIVED:
                    item += "Đơn hàng giao không thành công !";
                default:
                    throw new PuddyException(PuddyCode.INTERNAL_SERVER);
            }
            result.add(item);
        });
        return result;
    }

    public static String getStatusCombination(String status, Date updatedDate, UserEntity lastModifiedUser) {
        StatusEnum statusEnum = StatusEnum.from(status);
        RoleEnum roleEnum = lastModifiedUser.getRole();
        String dateFmt = DateUtils.toStr(updatedDate, DateUtils.F_DDMMYYYYHHMM);
        String combinationName = lastModifiedUser.getFirstName() + " " + lastModifiedUser.getLastName();
        switch (statusEnum) {
            case PENDING:
                return "Đang chờ xứ lý";
            case CANCEL:
                return dateFmt + " - Hủy bởi khách hàng";
            case REJECT:
                return dateFmt + " - Đã bị từ chối bởi (" + roleEnum.getName() + ") " + combinationName;
            case ACCEPT:
                return dateFmt + " - Được chấp nhận bởi (" + roleEnum.getName() + ") " + combinationName;
            default:
                break;
        }
        return null;
    }
}
