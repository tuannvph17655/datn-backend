package com.datn.service.impl;

import com.datn.dto.admin.user.personal.PersonalDto;
import com.datn.service.UserInfoService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
	private final PuddyRepository repository;

	@Override
	public Object personal(CurrentUser currentUser) {
		log.info("start personal");
		AuthValidator.checkLogin(currentUser);
		var user = repository.userRepository.findByIdAndActive(currentUser.getId(), true);
		if (null == user) {
			throw new PuddyException(PuddyCode.USER_LOCKED);
		}
		return ResData.ok(PersonalDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.dob(user.getDob())
				.email(user.getEmail())
				.gender(user.getGender())
				.phone(user.getPhone())
				.build());
	}
}
