package com.datn.repository.custom.impl;

import com.datn.dto.admin.detail.DetailRes;
import com.datn.dto.admin.detail.ItemDto;
import com.datn.dto.admin.detail.StatusDto;
import com.datn.repository.custom.OrderCustomRepository;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.MoneyUtils;
import com.datn.utils.common.OrderUtils;
import com.datn.utils.constants.PuddyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    @Override
    public ResData<DetailRes> detail4Admin(CurrentUser currentUser, String id) {
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        if (!repository.orderRepository.existsById(id)) {
            return new ResData<>(true);
        }
        DetailRes.DetailResBuilder res = DetailRes.builder().id(id);

        List<ItemDto> items = repository.orderDetailRepository.getItemList(id);
        if (!items.isEmpty()) {
            for (ItemDto obj : items) {
                obj.setPriceFmt(MoneyUtils.format(obj.getPrice()));
                obj.setTotalFmt(MoneyUtils.format(obj.getTotal()));
            }
        }
        res.items(items);

        List<StatusDto> orderStatusList = repository.orderStatusRepository.findHistory(id);
        if (!orderStatusList.isEmpty()) {
            res.history(OrderUtils.getHistory(orderStatusList));
        }

        return new ResData<>(res.build(), PuddyCode.OK);
    }

}
