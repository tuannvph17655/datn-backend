package com.datn.utils.base;


import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
@Slf4j
public abstract class PuddyController {
    @Autowired
    protected PuddyService service;

    protected CurrentUser getCurrentUser() {
        var repository = BeanUtils.getBean(PuddyRepository.class);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var id = authentication.getPrincipal().toString().replace("\"", "").trim();
        if (StringUtils.isNullOrEmpty(id)) {
            throw new PuddyException(PuddyCode.USER_NOT_FOUND);
        }
        var currentUser = repository.userRepository.findCurrentUserByIdAndActive(id);
        if (currentUser == null) {
            throw new PuddyException(PuddyCode.MUST_LOGIN);
        }
        return currentUser;
    }
}
