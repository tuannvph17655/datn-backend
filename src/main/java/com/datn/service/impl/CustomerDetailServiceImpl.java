package com.datn.service.impl;

import com.datn.dto.customer.user.register.RegisterDto;
import com.datn.entity.UserEntity;
import com.datn.service.CustomerDetailService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.DateUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.validator.user.RegisterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final PuddyRepository repository;

    //    @Value("${SPRING_MAIL_USERNAME}")
    private String email;

    private static final String DOMAIN_CLIENT = "http://localhost:4200/reset-password/%s";
    private static final long MINUS_TO_EXPIRED = 10;
    private static final String RESET_PASSWORD_TEMPLATE_NAME = "reset_password.ftl";
    private static final String FROM = "Puddy<%s>";
    private static final String SUBJECT = "QUÊN MẬT KHẨU";

    @Override
    public Object register(RegisterDto dto) {
        RegisterValidator.validDtoData(dto);
        RegisterValidator.validDtoConstrains(dto, repository);
        PasswordEncoder passwordEncoder = BeanUtils.getBean(BCryptPasswordEncoder.class);
        var user = UserEntity.builder()
                .id(UidUtils.generateUid())
                .role(RoleEnum.ROLE_CUSTOMER)
                .active(true)
                .firstName(dto.getFirstName().trim())
                .lastName(dto.getLastName().trim())
                .gender(Boolean.getBoolean(dto.getGender()))
                .dob(DateUtils.toDate(dto.getDob(), DateUtils.F_DDMMYYYY))
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone().trim())
                .email(dto.getEmail().trim())
                .build();
        log.info("register() user before save: {}", JsonUtils.toJson(user));
        repository.userRepository.save(user);
        log.info("register() user after save: {}", JsonUtils.toJson(user));

        return ResData.ok(user.getId());
    }
}
