package edu.hhuc.yixiang.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 16:49:42
 */
public class DateUtil {
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_HMS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String formatNow() {
        return DateUtil.formatYmdHms(new Date());
    }

    public static String formatNowPrecise() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YMD_HMS_SSS);
        return simpleDateFormat.format(new Date());
    }

    public static String formatYmdHms(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YMD_HMS);
        return simpleDateFormat.format(new Date());
    }

    public static long durationBetween(Date startTime, Date endTime) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        return endTime.getTime() - startTime.getTime();
    }
}
