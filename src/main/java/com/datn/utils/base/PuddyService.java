package com.datn.utils.base;

import com.datn.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PuddyService {
        public final AddressService addressService;
    public final CategoryService categoryService;
    public final ColorService colorService;
    //    public final LocationService locationService;
    public final MaterialService materialService;
//    public final EmailLogService emailLogService;
    public final OrderService orderService;
    public final OrderDetailService orderDetailService;
//    public final OrderStatusService orderStatusService;
    public final ProductOptionService productOptionService;
    public final ProductService productService;
    //    public final ResetTokenService resetTokenService;
    public final UserService userService;
    public final CartService cartService;
    public final SizeService sizeService;

    public final EventsService eventService;
    public final SuggestService suggestService;

    public final VoucherService voucherService;//    public final SuggestService suggestService;
//    public final DashboardService dashboardService;
    public final AdminProductService adminProductService;
//    public final MailService mailService;
//    public final AdminUserService adminUserService;
//    public final AdminUserInfoService adminUserInfoService;
    public final UserInfoService userInfoService;
//    public final AdminReportService adminReportService;
//    public final OrderDiscountService orderDiscountService;
    public final CustomerDetailService customerDetailService;
    public final ProductInfoService productInfoService;
//    public final CategoryInfoService categoryInfoService;
//    public final CustomerInfoService customerInfoService;
//    public final AdminOrderDetailService adminOrderDetailService;
//    public final AdminOrderDetailServiceV2 adminOrderDetailServiceV2;
//    public final AdminCustomerDetailService adminCustomerDetailService;
}
