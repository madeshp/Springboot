package com.springboot.learning.utils;


/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

/**
 * Utility class for convert Pojo to json for logging purposes.
 */
@Component
public class JsonUtils {
   // private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper jsonMapper;
//    private final boolean applyMasking;
//    private final JsonMaskingUtil jsonMaskingUtil;
@Autowired
ObjectMapper objectMapper;
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
    /*public LoggerUtils(final boolean applyMasking, final JsonMaskingUtil jsonMaskingUtil) {
        this.applyMasking = applyMasking;
        this.jsonMaskingUtil = jsonMaskingUtil;
    }
*/
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
        }
        catch (final JsonProcessingException e) {
           // LOGGER.error("Failed writing object as json", e);
        }
/*
        if (applyMasking) {
            jsonRepresentation = jsonMaskingUtil.mask(jsonRepresentation);
        }*/
        return jsonRepresentation;
    }


    public String logJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
           // LOGGER.error("json error: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Returns JSON string for given object, null if error.
     *
     * @param obj
     * @return String
     */
    public String toJson(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
          //  LOGGER.error("json processing error: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     *
     * @param responseString
     * @param type
     * @return
     */
    public <T> T fromJson(String responseString, Class<T> type) {
        try {
            return objectMapper.readValue(responseString, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Encode to String.
     *
     * @param bytes
     * @return String
     */
    public String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decode base64 String
     *
     * @param s - String
     * @return byte[]
     */
    public byte[] base64Decode(String s) {
        return Base64.getDecoder().decode(s);
    }

    public String logJsonPrettyPrint(final Object obj) {
        if (null != obj) {
            try {
                if (obj instanceof String ) {
                    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fromJsonString((String)obj, Object.class));
                }
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } catch (IOException e) {
             //   LOGGER.debug("prettyPrint failed: {}", e.getMessage());
            }
        }
        return null;
    }

    public <T> T fromJsonString(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
           // LOGGER.error("json error: {}", e.getMessage(), e);
        }
        return null;
    }


}

