package com.datn.utils.base.rest;

import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyConst;
import lombok.Data;

@Data
public class PageReq {

    //số trang, mặc định bắt đầu bằng 0
    private Integer page = PuddyConst.Values.PAGE_DEFAULT;

    //số phần tử tối đá trong 1 trang
    private Integer pageSize = PuddyConst.Values.PAGE_SIZE_DEFAULT;

    //sắp xếp theo thuộc tính nào?
    private String sortField;

    //sắp xếp theo thứ tự gì: asc - desc
    private String sortDirection = PuddyConst.Values.SORT_DIRECTION_DEFAULT;

    public PageReq(Integer page, Integer pageSize, String sortField, String sortDirection) {
        if (page == null || page < 1) {
            page = PuddyConst.Values.PAGE_DEFAULT;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = PuddyConst.Values.PAGE_SIZE_DEFAULT;
        }
        if (StringUtils.isNullOrEmpty(sortField)) {
            sortField = PuddyConst.Values.SORT_FIELD_DEFAULT;
        }
        if (StringUtils.isNullOrEmpty(sortDirection)) {
            sortDirection = PuddyConst.Values.SORT_DIRECTION_DEFAULT;
        }
        this.page = page;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }
}
