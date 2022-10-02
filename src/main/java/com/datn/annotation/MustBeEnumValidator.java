package com.datn.annotation;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Supplier;

import static com.datn.utils.common.EnumUtils.getEnumNames;

public class MustBeEnumValidator extends AnnotationValidator<MustBeEnum , String>{
    @Override
    public AnnotationValidationResult validate(@Nullable String value, @NotNull MustBeEnum annotation) {
        final boolean ignoreCase = annotation.ignoreCase();

        Set<String> items = getEnumNames(annotation.value());
        final Supplier<String> messageProvider = () -> {
            final String requiredValues = Joiner.on(", ").join(items);
            return String.format(annotation.message(), requiredValues);
        };

        if (Strings.isBlank(value)) {
            return annotation.allowNull()
                    ? AnnotationValidationResult.succeed()
                    : AnnotationValidationResult.failed(messageProvider);
        }

        value = ignoreCase ? value.trim().toUpperCase() : value;
        final boolean isValid = items.contains(value);

        return AnnotationValidationResult.builder().valid(isValid).messageProvider(messageProvider).build();
    }
}
