package com.janwee.rabbitmqinaction.springintegration.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object o) {
        Assert.notNull(o, "object not nullable.");
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("error occurs whiling serializing object to json.", e);
        }
        return "";
    }

    public static <T> T fromJson(String json, Class<T> c) {
        try {
            return MAPPER.readValue(json, c);
        } catch (Exception e) {
            log.error("error occurs whiling deserializing json to object." + json, e);
        }
        return null;
    }
}
