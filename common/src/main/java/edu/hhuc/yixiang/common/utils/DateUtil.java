package edu.hhuc.yixiang.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YMD_HMS);
        return simpleDateFormat.format(new Date());
    }

    public static String formatNowPrecise() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YMD_HMS_SSS);
        return simpleDateFormat.format(new Date());
    }
}
