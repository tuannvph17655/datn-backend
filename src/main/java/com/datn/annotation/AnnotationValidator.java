package com.datn.annotation;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.function.Supplier;

public abstract class AnnotationValidator<T extends Annotation,V> implements ConstraintValidator<T,V> {
    protected T annotation;
    @Override
    public void initialize(T constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(@Nullable V value, @NotNull ConstraintValidatorContext context) {
        final AnnotationValidationResult result = validate(value, annotation);

        final boolean isValid = result.isValid();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(result.getMessageProvider().get()).addConstraintViolation();
        }

        return isValid;
    }

    abstract public AnnotationValidationResult validate(@Nullable V value, @NotNull T annotation);

    @Getter
    @Builder
    public static class AnnotationValidationResult {
        private boolean valid;

        private Supplier<String> messageProvider;

        public boolean isInvalid(){
            return !valid;
        }

        public static AnnotationValidationResult succeed(){
            return builder().valid(true).build();
        }

        public static AnnotationValidationResult failed(Supplier<String> messageProvider){
            return builder().valid(false).messageProvider(messageProvider).build();
        }
    }
}
