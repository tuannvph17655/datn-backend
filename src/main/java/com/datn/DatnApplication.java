package com.datn;

import com.datn.utils.common.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
@EnableScheduling
public class DatnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatnApplication.class, args);
        PasswordEncoder passwordEncoder = BeanUtils.getBean(BCryptPasswordEncoder.class);
        System.out.println("success");
        log.info("admin123 encode: {}", passwordEncoder.encode("admin123"));
    }

}
