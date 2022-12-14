package com.datn.service.impl;

import com.datn.dto.admin.user.personal.PersonalDto;
import com.datn.dto.customer.user.ProfileDto;
import com.datn.service.UserInfoService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.auth.AuthValidator;
import com.datn.utils.validator.user.ProfileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Override
	public Object updateProfile(CurrentUser currentUser, ProfileDto dto) {
		log.info("start updateProfile with payload: {}", JsonUtils.toJson(dto));
		AuthValidator.checkLogin(currentUser);
		var user = repository.userRepository.findByIdAndActive(dto.getId(), true);
		if (null == user) {
			throw new PuddyException(PuddyCode.INTERNAL_SERVER);
		}
		if (!currentUser.getId().equals(dto.getId())) {
			throw new PuddyException(PuddyCode.FORBIDDEN);
		}

		ProfileValidator.validDto(dto);
		var passwordEncoder = BeanUtils.getBean(PasswordEncoder.class);
		var password = user.getPassword();
		if (!StringUtils.isNullOrEmpty(dto.getNewPassword())) {
			password = passwordEncoder.encode(dto.getNewPassword());
		}
		user.setFirstName(dto.getFirstName().trim())
				.setLastName(dto.getLastName().trim())
				.setPhone(dto.getPhone().trim())
				.setGender(dto.getGender())
				.setDob(dto.getDob())
				.setPassword(password);
		log.info("updateProfile before save: {}", JsonUtils.toJson(user));
		repository.userRepository.save(user);
		log.info("updateProfile after save: {}", JsonUtils.toJson(user));
		return ResData.ok(user.getId());
	}

}
