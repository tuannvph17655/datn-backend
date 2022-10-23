package com.datn.utils.base.rest;

import com.datn.utils.constants.PuddyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {
    private List<T> data;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private Integer statusCode;
    private String message;
    private Date timeStamp;

    public PageData(List<T> data, Integer page, Integer pageSize, Long totalElements, Integer statusCode, String message) {
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
        if (!Objects.equals(0L, totalElements) && Objects.equals(0, totalElements % pageSize)) {
            this.totalPages = Math.toIntExact(totalElements / pageSize);
        } else {
            this.totalPages = 0;
        }
        this.totalElements = totalElements;
        this.statusCode = statusCode;
        this.message = message;
        timeStamp = new Date();
    }

    public PageData(List<T> data, Integer page, Integer pageSize, Long totalElements, PuddyCode puddyCode) {
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
        if (totalElements % pageSize == 0) {
            this.totalPages = Math.toIntExact(totalElements / this.pageSize);
        } else {
            this.totalPages = Math.toIntExact(totalElements / this.pageSize) + 1;
        }
        this.totalElements = totalElements;
        this.statusCode = Integer.parseInt(puddyCode.getCode());
        this.message = puddyCode.getMessage();
        timeStamp = new Date();
    }

    public PageData(Boolean isEmpty) {
        if (Boolean.TRUE.equals(isEmpty)) {
            this.data = Collections.emptyList();
            this.page = 0;
            this.pageSize = 0;
            this.totalPages = 0;
            this.totalElements = 0L;
            this.statusCode = Integer.parseInt(PuddyCode.INTERNAL_SERVER.getCode());
            this.message = HttpStatus.NO_CONTENT.getReasonPhrase();
            timeStamp = new Date();
        }
    }

    public static PageData<Object> setResult(List<Object> data, Integer page, Integer pageSize, Long totalElements, PuddyCode puddyCode) {
        PageData<Object> pageData = new PageData<>();
        pageData.setData(data)
                .setPage(page)
                .setPageSize(pageSize)
                .setTotalElements(totalElements)
                .setTotalPages(getTotalPages(totalElements, pageSize))
                .setStatusCode(Integer.parseInt(puddyCode.getCode()))
                .setMessage(puddyCode.getMessage())
                .setTimeStamp(new Date());
        return pageData;
    }

    private static Integer getTotalPages(Long totalElements, Integer pageSize) {
        if (totalElements % pageSize == 0) {
            return Math.toIntExact(totalElements / pageSize);
        } else {
            return Math.toIntExact(totalElements / pageSize) + 1;
        }
    }

    public static <T> PageData setResult(List<T> data, Integer page, Integer pageSize, Long totalElements) {
        return new PageData(data, page, pageSize, totalElements, PuddyCode.OK);
    }

    public static PageData<?> setEmpty(PageReq pageReq) {
        PageData pageData = new PageData<>();
        pageData.setData(Collections.emptyList());
        pageData.setPage(pageReq.getPage());
        pageData.setPageSize(pageReq.getPageSize());
        pageData.setTotalPages(0);
        pageData.setTotalElements(0L);
        pageData.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        pageData.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        pageData.setTimeStamp(new Date());
        return pageData;
    }
}
