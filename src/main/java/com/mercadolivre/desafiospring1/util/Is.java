package com.mercadolivre.desafiospring1.util;

import java.math.BigDecimal;

public class Is {

    public static boolean isNumericException(String str) {
        if (str == null)
            return false;
        try {
            new BigDecimal(String.valueOf(str));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isBooleanException(String str) {
        if (str == null)
            return false;

        return Boolean.parseBoolean(str);
    }
}


