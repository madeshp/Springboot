package com.springboot.learning.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonMaskingUtil {
    private static final String PATTERN_COMPILE_STR = "\"%s\" : \"(.*)\"";
    private static final String REPLACEMENT_REGEX = "\"$1\" : \"****\"";
    private Pattern patterns;

    /**
     * Constructor.
     *
     * @param fieldsToMask
     *          - should be json key names separated by |
     */
    public JsonMaskingUtil(final String fieldsToMask) {
        final String fieldGroup = StringUtils.trimToNull(fieldsToMask);
        if (fieldGroup != null) {
            final String fieldsToMaskGroup = "(" + fieldGroup.trim() + ")";
            patterns = Pattern.compile(String.format(PATTERN_COMPILE_STR, fieldsToMaskGroup));
        }
    }

    /**
     * Returns string representing input string masked.
     *
     * @param input
     *          - string to mask.
     * @return returns input with fields masked.
     */
    public String mask(final String input) {
        Validate.notNull(patterns, "No masking fields have been provided");
        if (StringUtils.isNotEmpty(input)) {
            final Matcher matcher = patterns.matcher(input);
            return matcher.replaceAll(REPLACEMENT_REGEX);
        }
        else {
            return input;
        }
    }
}
