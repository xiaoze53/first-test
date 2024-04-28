package com.hbsi.jdbc.framework.util;

import java.util.Date;

public class DateUtil {
    public static Date sqlDateToUtilDate(Date date) {
        return new Date(date.getTime());
    }
}
