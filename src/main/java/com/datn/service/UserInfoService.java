package com.datn.service;

import com.datn.dto.customer.user.ProfileDto;
import com.datn.utils.base.rest.CurrentUser;

public interface UserInfoService {

	Object updateProfile(CurrentUser currentUser, ProfileDto dto);
	Object personal(CurrentUser currentUser);
}
