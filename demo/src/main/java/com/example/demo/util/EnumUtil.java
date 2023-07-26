package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class EnumUtil {
    /**
     * @param enumType
     * @param targetValue
     * @param <E> 
     * @return true if enumType contains targetValue
     */
    public static <E extends Enum<E>> boolean contains(Class<E> enumType, String targetValue) {
        if (enumType == null || targetValue == null) {
            return false;
        }

        for (E enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.toString().equals(targetValue)) {
                return true;
            }
        }
        return false;
    }
}