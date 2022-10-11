package com.datn.service.impl;

import com.datn.dto.customer.user.register.RegisterDto;
import com.datn.entity.ResetTokenEntity;
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

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    @Transactional
    public Object sendMail4ForgotPass(String email) {
//        log.info("sendMail4ForgotPass() start with payload: {}", email);
//        if (StringUtils.isNullOrEmpty(email)) {
//            throw new PuddyException(PuddyCode.EMAIL_NOT_BLANK);
//        }
//        var customer = repository.userRepository.findByEmailIgnoreCaseAndActiveAndRole(email.trim(), Boolean.TRUE, RoleEnum.ROLE_CUSTOMER);
//        log.info("sendMail4ForgotPass() find customer: {}", JsonUtils.toJson(customer));
//        if (null == customer) {
//            throw new PuddyException(PuddyCode.ERROR_NOT_FOUND);
//        }
//        if (repository.resetTokenRepository.check5TimesInDay(customer.getId())) {
//            throw new PuddyException(PuddyCode.MAX_SEND_OTP);
//        }
//        var resetToken = ResetTokenEntity.builder()
//                .id(UidUtils.generateUid())
//                .token(UidUtils.generateToken(6))
//                .userId(customer.getId())
//                .createdDate(new Date())
//                .build();
//        log.info("sendMail4ForgotPass() resetToken before save: {}", JsonUtils.toJson(resetToken));
//        repository.resetTokenRepository.save(resetToken);
//        log.info("sendMail4ForgotPass() resetToken after save: {}", JsonUtils.toJson(resetToken));
//
//        var model = buildModel(customer, resetToken);
//
//        try {
//            var template = configuration.getTemplate(RESET_PASSWORD_TEMPLATE_NAME);
//            var rsmd = ResetPasswordMailDto.builder()
//                    .from(String.format(FROM, email))
//                    .to(customer.getEmail())
//                    .text(FreeMarkerTemplateUtils.processTemplateIntoString(template, model))
//                    .subject(SUBJECT)
//                    .build();
//            log.info("sendMail4ForgotPass() build rsmd: {}", JsonUtils.toJson(rsmd));
//            mailService.sendWForgotPassword(rsmd).get();
//            return ResData.ok(resetToken.getId());
//        } catch (Exception e) {
//            log.error("sendMail4ForgotPass() error: {}", e.getMessage());
//            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
//        }
        return null;
    }

    private static Map buildModel(UserEntity customer, ResetTokenEntity resetToken) {
        Map<String, Object> model = new HashMap<>();
        model.put("time", DateUtils.toStr(new Date(), DateUtils.F_DDMMYYYYHHMM));
        model.put("name", customer.getFirstName() + " " + customer.getLastName());
        model.put("email", customer.getEmail());
        model.put("link", String.format(DOMAIN_CLIENT, resetToken.getToken()));
        model.put("minusToExpired", MINUS_TO_EXPIRED);
        log.info("sendMail4ForgotPass() build model: {}", JsonUtils.toJson(model));
        return model;
    }
}
