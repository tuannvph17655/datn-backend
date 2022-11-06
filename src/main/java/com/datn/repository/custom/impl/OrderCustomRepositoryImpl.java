package com.datn.repository.custom.impl;

import com.datn.dto.admin.detail.DetailRes;
import com.datn.dto.admin.detail.ItemDto;
import com.datn.dto.admin.detail.PriceResult;
import com.datn.dto.admin.detail.StatusDto;
import com.datn.dto.customer.order.OrderDetailRes;
import com.datn.entity.OrderEntity;
import com.datn.repository.custom.OrderCustomRepository;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.ApiUtils;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.MoneyUtils;
import com.datn.utils.common.OrderUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;
import com.datn.utils.constants.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    @Override
    public ResData<DetailRes> detail4Admin(CurrentUser currentUser, String id) {
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        OrderEntity order = repository.orderRepository.findById(id).orElseThrow(
                ()-> new PuddyException(PuddyCode.BAD_REQUEST, "Không tìm thấy đơn hàng tương ứng !")
        );
        log.info("order {}",order);

        DetailRes.DetailResBuilder res = DetailRes.builder().id(id);

        List<ItemDto> items = repository.orderDetailRepository.getItemList(id);
        Long shopPrice = items.stream().map(ItemDto::getTotal).collect(Collectors.toList()).stream().reduce(0L, Long::sum);

        res.items(items);
        PriceResult priceResult = PriceResult.builder().ship(order.getShipPrice()).shop(shopPrice).total(order.getTotal()).build();
        res.priceResult(priceResult);

        List<StatusDto> orderStatusList = repository.orderStatusRepository.findHistory(id);
        if (!orderStatusList.isEmpty()) {
            res.history(OrderUtils.getHistory(orderStatusList));
        }
        OrderDetailRes orderDetailRes = repository.orderDetailRepository.getOrderDetail(id);
        String statusOrderValue =  StatusEnum.from(orderDetailRes.getStatusOrder()).getName();
        DetailRes.OrderInfoRes orderInfoRes = DetailRes.OrderInfoRes.builder().build();
        ApiUtils.copy(orderDetailRes, orderInfoRes);
        orderInfoRes.setStatusOrderValue(statusOrderValue);
        res.orderInfo(orderInfoRes);
        return new ResData<>(res.build(), PuddyCode.OK);
    }
}
