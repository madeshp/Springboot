package com.springboot.learning.Logger;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.learning.utils.JsonMaskingUtil;


public class LoggerUtils {
    //private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);

    private static final ObjectMapper jsonMapper;
    private final boolean applyMasking;
    private final JsonMaskingUtil jsonMaskingUtil;

    static {
        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jsonMapper.setSerializationInclusion(Include.NON_NULL);
        jsonMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
    }

    /**
     * Constructor.
     *
     * @param applyMasking
     *          - indicates if masking is enabled.
     * @param jsonMaskingUtil
     *          - masking utility.
     */
    public LoggerUtils(final boolean applyMasking, final JsonMaskingUtil jsonMaskingUtil) {
        this.applyMasking = applyMasking;
        this.jsonMaskingUtil = jsonMaskingUtil;
    }

    /**
     * Returns json string representation of POJO.
     *
     * @param object
     *          - POJO to convert to json.
     * @return String representing POJO in json format.
     */
    public String writeAsJson(final Object object) {
        String jsonRepresentation = null;
        try {
            if (object != null) {
                jsonRepresentation = jsonMapper.writeValueAsString(object);
            }
        } catch (final JsonProcessingException e) {
         //   LOGGER.error("Failed writing object as json", e);
        }

        if (applyMasking) {
            jsonRepresentation = jsonMaskingUtil.mask(jsonRepresentation);
        }
        return jsonRepresentation;
    }}
