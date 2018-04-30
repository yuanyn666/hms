package com.hms.util;

import com.hms.service.TimeConstants;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by wb-wyh270612 on 2017/5/4.
 */
public class DateUtils {

    public static final String patternmonthday = "M月d日";
    public static final String patternDate = "yyyy-MM-dd";
    public static final String patternyyMMdd = "yyMMdd";
    public static final String patternyyyyMMdd = "yyyyMMdd";
    public static final String patternShortYearMonthDay = "yy/MM/dd";
    public static final String patternInclineDateString = "yyyy/MM/dd";
    public static final String patternSpotDateString = "yyyy.MM.dd";
    public static final String patternYearMonth = "yyyy-MM";
    public static final String patternYearMonthText = "yyyy年MM月";
    public static final String patternYearMonthValue = "yyyyMM";
    public static final String patternDateTime = "yyyy-MM-dd HH:mm:ss";
    public static final String patternYearMonthDayHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String patternDateMinutes = "yyyy-MM-dd HH:mm";
    public static final String patternTextDate = "第w周 EEE yyyy年M月d日";
    public static final String patternCnDateTimeShort = "yyyy年M月d日";
    public static final String patternCnDateTime = "yyyy年M月d日 H点m分s秒";
    private static final String patternMinutes = "HH:mm";
    private static final String patternDateyyyyMMdd = "yyyyMMdd";

    public static String getTimeDifference(Date end, Date start) {

        long s = start.getTime();
        long e = end.getTime();

        return getTimeDiff(e, s);
    }

    public static String getTimeDifference(String end, String start) throws Exception {

        long s = parseStr2DateTime(start).getTime();
        long e = parseStr2DateTime(end).getTime();

        return getTimeDiff(e, s);
    }

    private static String getTimeDiff(long end, long start) {

        int diff = (int) ((end - start) / TimeConstants.THOUSAND);

        return sec2Time(diff);
    }

    public static String getTimeDifference(Date end, String start) throws Exception {

        long s = parseStr2DateTime(start).getTime();
        long e = end.getTime();

        return getTimeDiff(e, s);
    }

    public static String getTimeDifference(String end, Date start) throws Exception {

        long s = start.getTime();
        long e = parseStr2DateTime(end).getTime();

        return getTimeDiff(e, s);
    }

    public static String sec2Time(int time) {

        int hourTimes = time / TimeConstants.ONE_HOUR_SECS;
        String timeStr = "";

        if (hourTimes > 0) {
            timeStr += hourTimes + ":";
            time -= hourTimes * TimeConstants.ONE_HOUR_SECS;
        } else {
            timeStr = "0:";
        }

        int minTimes = time / TimeConstants.SIXTY;
        if (minTimes > 0) {

            time -= minTimes * TimeConstants.SIXTY;
            if (minTimes > TimeConstants.NINE) {
                timeStr += minTimes + ":";
            } else {
                timeStr += "0" + minTimes + ":";
            }
        } else {
            timeStr += "00:";
        }

        if (time > TimeConstants.NINE) {
            timeStr += time;
        } else {
            timeStr += "0" + time;
        }

        return timeStr;
    }

    public static String parseDate2Str(Date d) {

        if (d == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(patternDateTime);
        return df.format(d);
    }

    public static String parseDate2Str2(Date d) {

        if (d == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(patternDateMinutes);
        return df.format(d);
    }

    public static Date parseStr2DateTime(String ds) throws Exception {

        if (StringUtils.isBlank(ds)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(patternDateTime);
        return df.parse(ds);
    }

    public static String parseDate2StrMin(Date ds) throws Exception {

        if (ds == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(patternMinutes);
        return df.format(ds);
    }

    public static Date parseStr2Date(String ds) throws Exception {

        if (StringUtils.isBlank(ds)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(patternDate);
        return df.parse(ds);
    }

    public static Date parseStr3Date(String ds) throws Exception {

        if (StringUtils.isBlank(ds)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(patternDateyyyyMMdd);
        return df.parse(ds);
    }



    public static String parseDate2Str(Date d, String pattern) {
        if (d == null) {
            return null;
        }

        if (StringUtils.isBlank(pattern)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(d);
    }

    public static Date parseStr2Date(String str, String pattern) throws ParseException {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(str);
    }

    /* 格式话日期为标准形式 */
    public static String formatDateTime(Date d, String pattern) {
        SimpleDateFormat fd = new SimpleDateFormat(pattern, Locale.CHINA);
        return fd.format(d);
    }

    /**
     * 计算最晚送达时间 - 当前时间   的分钟数
     *
     * @param arriveTime 送达时间    格式  HH:mm-HH:mm 或者 HH:mm
     * @return
     */
    public static int caculateRemainingTime(String arriveTime) {
        if (arriveTime.matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
            //最晚送达时间
            String[] timeArr = arriveTime.split("-");
            String lastestTime = timeArr[1];
            //当前时间
            Date now = new Date();
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            String nowTime = formatter.format(now);

            return parseMinutes(lastestTime) - parseMinutes(nowTime);
        } else if (arriveTime.matches("\\d{2}:\\d{2}")) {
            //当前时间
            Date now = new Date();
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            String nowTime = formatter.format(now);

            return parseMinutes(arriveTime) - parseMinutes(nowTime);
        } else {
            return Integer.MAX_VALUE;
        }

    }

    /**
     * 计算分钟数
     *
     * @param time 时间格式: HH:mm
     * @return
     */
    public static int parseMinutes(String time) {
        String[] arr = time.split(":");

        Integer hour = Integer.parseInt(arr[0]);
        Integer minute = Integer.parseInt(arr[1]);

        int result = hour * 60 + minute;
        return result;
    }

    public static String parseDate2StrDate(Date d) {

        if (d == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(patternDate);
        return df.format(d);
    }

}
