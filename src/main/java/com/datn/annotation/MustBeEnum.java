package com.datn.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {MustBeEnumValidator.class})
public @interface MustBeEnum {
    String message() default "Giá trị phải thuộc [%s]";

    Class<? extends Enum> value();

    boolean ignoreCase() default true;

    boolean allowNull() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
