package com.datn.utils.common;


import com.datn.utils.base.rest.PageReq;
import com.datn.utils.constants.PuddyConst;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

public class PageableUtils {

    private static final List<String> fieldIgnoreCases = Arrays.asList(
            "name",
            "address",
            "province",
            "firstName",
            "lastName",
            "email"
    );

    public static Pageable getPageable(PageReq req) {
        getPageReq(req);
        Sort.Direction direction = req.getSortDirection().equals("desc") ? Sort.Direction.DESC : Sort.Direction.DESC;
        Sort.Order order;
        if (fieldIgnoreCases.stream().anyMatch(item -> req.getSortField().equals(item))) {
            order = new Sort.Order(direction, req.getSortField()).ignoreCase();
        } else {
            order = new Sort.Order(direction, req.getSortField());
        }
        return PageRequest.of(req.getPage(), req.getPageSize(), Sort.by(order));
    }

    private static void getPageReq(PageReq req) {
        if (null == req.getPage() || req.getPage() < 0) {
            req.setPage(PuddyConst.Values.PAGE_DEFAULT);
        }
        if (null == req.getPageSize() || req.getPageSize() < 0) {
            req.setPageSize(PuddyConst.Values.PAGE_SIZE_DEFAULT);
        }
        if (StringUtils.isNullOrEmpty(req.getSortField())) {
            req.setSortField(PuddyConst.Values.SORT_FIELD_DEFAULT);
        }
        if (StringUtils.isNullOrEmpty(req.getSortDirection())) {
            req.setSortDirection(PuddyConst.Values.SORT_DIRECTION_DEFAULT);
        }
    }
}
