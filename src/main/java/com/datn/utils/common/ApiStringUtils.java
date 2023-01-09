package com.datn.utils.common;

import com.datn.errors.ServiceException;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.helpers.MessageFormatter;

import javax.annotation.Nullable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.datn.utils.Costs.un_accent_pattern;

public class ApiStringUtils {
    private final static String EMPTY = "";
    private final static String SPACE = " ";
    private final static String COMMA = ", ";
    private final static String LINE_BREAK = "\n";

    public static String join(String separator, Enum<?>... words) {
        return join(separator, ApiCollectionUtils.listOf(Enum::name, words));
    }

    public static String join(String separator, String... words) {
        return join(separator, Arrays.asList(words));
    }
    public static String join(String separator, Collection<String> words) {
        return Joiner.on(separator).join(words.stream().filter(Strings::isNotBlank).collect(Collectors.toList()));
    }

    public static String join(Collection<String> words) {
        return Joiner.on(COMMA).join(words.stream().filter(Strings::isNotBlank).collect(Collectors.toList()));
    }


    public static String joinLines(Collection<String> words) {
        return Joiner.on(LINE_BREAK).join(words.stream().filter(Strings::isNotBlank).collect(Collectors.toList()));
    }

    public static String retainDigits(final String text) {
        return CharMatcher.DIGIT.retainFrom(text);
    }

    /**
     * @author Sondd
     * Trim all spaces in a string.
     * @param text Can be null.
     * @param nullIfBlank When text is blank, return empty string if true. Otherwise, return null.
     */
    public static @Nullable String removeAllSpaces(@Nullable final String text, boolean nullIfBlank) {
        if (Strings.isBlank(text)) {
            return nullIfBlank ? null : EMPTY;
        }
        return join(EMPTY, text.split(SPACE));
    }

    /**
     * @author Sondd
     * Trim all spaces in a string.
     * @param text Can be null.
     * @return return empty string if input is blank, otherwise, return the string which is trimmed.
     */
    public static String removeAllSpaces(@Nullable final String text) {
        return removeAllSpaces(text, true);
    }

    /**
     * @author Sondd
     * Clean the name of a place or a human by trimming redundant space
     * and uppercase only first character of each word (proper case).
     */
    public static String cleanName(String name) {
        if (name == null) {
            return EMPTY;
        }
        name = name.toLowerCase();

        final List<String> words = Arrays.stream(name.split(SPACE))
                .filter(org.apache.commons.lang3.StringUtils::isNotBlank)
                .map(org.apache.commons.lang3.StringUtils::capitalize)
                .collect(Collectors.toList());

        return Joiner.on(SPACE).join(words);
    }

    public static String emptyIfNull(String input){
        return input == null || org.apache.commons.lang3.StringUtils.isBlank(input) ? EMPTY : input.trim();
    }

    public static String snakeCaseOf(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }

    public static Optional<String> between(final @Nullable String start, final @Nullable String end, final @Nullable String parent) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(parent)) {
            return Optional.empty();
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(start) && org.apache.commons.lang3.StringUtils.isEmpty(end)) {
            return Optional.empty();
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(start)) {
            String result = beforeOf(end, parent);
            return result.isEmpty() ? Optional.empty() : Optional.of(result);
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(end)) {
            String result = afterOf(start, parent);
            return result.isEmpty() ? Optional.empty() : Optional.of(result);
        }
        int startIndex = parent.indexOf(start);
        if (startIndex < 0) {
            return Optional.empty();
        }
        int endIndex = parent.indexOf(end);
        if (endIndex < 0) {
            return Optional.empty();
        }
        if (startIndex + start.length() >= endIndex) {
            return Optional.empty();
        }
        return Optional.of(parent.substring(startIndex + start.length(), endIndex));
    }

    public static String removeLines(final @Nullable String inputWithLines) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(inputWithLines)) {
            return "";
        }
        String[] lines = inputWithLines.split("\\\\n");
        return join("", lines);
    }

    @Data
    @Builder
    @Deprecated
    public static class StringConverter{
        private String input;
        private String output;

        public StringConverter unAccent(){
            emptyIfNull();
            try {
                String temp = Normalizer.normalize(this.input, Normalizer.Form.NFD);
                this.output = un_accent_pattern.matcher(temp)
                        .replaceAll("")
                        .replaceAll("đ", "d");
            } catch (Exception ex) {ex.printStackTrace();}
            return this;
        }

        public StringConverter toLowerCase(){
            this.output = this.output.toLowerCase();
            return this;
        }

        public StringConverter toUpperCase(){
            this.output = this.output.toUpperCase();
            return this;
        }

        private void emptyIfNull(){
            if (StringUtils.isBlank(this.input)) this.input = "";
        }
    }

    public static boolean isEqualsIgnoreCase(final String first, final String second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return first.equalsIgnoreCase(second);
    }

    public static boolean isNotEqual(final String first, final String second) {
        return !isEqualsIgnoreCase(first, second);
    }



    @NotNull
    public static String camelCaseOf(@Nullable final String word) {
        if (word == null) {
            return "";
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, word);
    }

    @NotNull
    public static String afterOf(@Nullable final String word, @Nullable final String parent) {
        if (word == null || parent == null) {
            return EMPTY;
        }
        if (word.isEmpty()) {
            return parent;
        }
        int index = parent.indexOf(word);
        if (index < 0) {
            return EMPTY;
        }
        return parent.substring(index + 1);
    }

    @NotNull
    public static String afterLastOf(@Nullable final String word, @Nullable final String parent) {
        if (word == null || parent == null) {
            return EMPTY;
        }
        if (word.isEmpty()) {
            return parent;
        }
        int index = parent.lastIndexOf(word);
        if (index < 0) {
            return EMPTY;
        }
        return parent.substring(index + 1);
    }

    @NotNull
    public static String beforeOf(@Nullable final String word, @Nullable final String parent) {
        if (word == null || parent == null) {
            return EMPTY;
        }
        int index = parent.indexOf(word);
        if (index < 0) {
            return EMPTY;
        }
        return parent.substring(0, index);
    }

    @NotNull
    public static String beforeLastOf(@Nullable final String word, @Nullable final String parent) {
        if (word == null || parent == null) {
            return EMPTY;
        }
        int index = parent.lastIndexOf(word);
        if (index < 0) {
            return EMPTY;
        }
        return parent.substring(0, index);
    }

    @NotNull
    public static String lowerOf(@Nullable final String str) {
        return Optional.ofNullable(str).map(x -> x.trim().toLowerCase()).orElse("");
    }

    @NotNull
    public static String upperOf(@Nullable final String str) {
        return Optional.ofNullable(str).map(x -> x.trim().toUpperCase()).orElse("");
    }

    public static String removeAccent(String input) {
        if (Strings.isBlank(input)) {
            return EMPTY;
        }
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace("đ", "d");
    }

    // For more information, please visit this page: https://stackoverflow.com/a/43262120
    public static String format(String original, Object... replacements)
    {
        if (Pattern.matches(".*\\{\\d+,.+,.+\\}.*", original))
        {
            return MessageFormat.format(original, replacements);
        }
        else
        {
            String r = "\\{\\d\\}";

            if(original.matches(".*" + r + ".*"))
            {
                Pattern p = Pattern.compile(r);
                Matcher m = p.matcher(original);
                StringBuffer b = null;
                int i = 0;

                while(m.find())
                {
                    if(b == null) b = new StringBuffer();
                    m.appendReplacement(b, replacements[i].toString());
                    i++;
                }
                m.appendTail(b);
                return (i > 0) ? b.toString() : null;
            }
            else
            {
                return MessageFormatter.arrayFormat(original, replacements).getMessage();
            }
        }
    }


}
