package cn.kirbyhao.core.date;

import cn.kirbyhao.core.util.ObjectUtils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间与日期通用处理工具类，均采用java.time包，线程安全
 *
 * @author Lu Hao
 * @date 2020-11-22
 */
public class DateTimeUtil {

    /**
     * 精确到日的日期格式
     */
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd";

    /**
     * 精确到秒的日期格式
     */
    public static final String DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的日期格式
     */
    public static final String DATETIME_MILLIS_FORMAT_STR = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 当前时间，默认时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用默认时区
     *
     * @param instant {@link Instant}
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用UTC时区
     *
     * @param instant {@link Instant}
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofUtc(Instant instant) {
        return of(instant, ZoneId.of("UTC"));
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param zoneId 时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        if (null == instant) {
            return null;
        }

        return LocalDateTime.ofInstant(instant, ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault()));
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param timeZone 时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, TimeZone timeZone) {
        if (null == instant) {
            return null;
        }

        return of(instant, ObjectUtils.defaultIfNull(timeZone, TimeZone.getDefault()).toZoneId());
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用默认时区
     *
     * <p>注意：此方法使用默认时区，如果非UTC，会产生时间偏移</p>
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用UTC时区
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ofUtc(long epochMilli) {
        return ofUtc(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * 毫秒转{@link LocalDateTime}，根据时区不同，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param zoneId 时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, ZoneId zoneId) {
        return of(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    /**
     * 毫秒转{@link LocalDateTime}，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param timeZone 时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, TimeZone timeZone) {
        return of(Instant.ofEpochMilli(epochMilli), timeZone);
    }

    /**
     * {@link Date}转{@link LocalDateTime}，使用默认时区
     *
     * @param date Date对象
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Date date) {
        if (null == date) {
            return null;
        }

        return of(date.toInstant());
    }

    /**
     * {@link TemporalAccessor}转{@link LocalDateTime}，使用默认时区
     *
     * @param temporalAccessor {@link TemporalAccessor}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        if(temporalAccessor instanceof LocalDate){
            return ((LocalDate)temporalAccessor).atStartOfDay();
        }

        return LocalDateTime.of(
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.DAY_OF_MONTH),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.HOUR_OF_DAY),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.NANO_OF_SECOND)
        );
    }


    /**
     * 获取当前时间。用 yyyy-MM-dd 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDate() {
        return format(DATE_FORMAT_STR);
    }

    /**
     * 获取当前时间。用 yyyy-MM-dd HH:mm:ss 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDateTime() {
        return format(DATETIME_FORMAT_STR);
    }

    /**
     * 获取当前时间。用 yyyy-MM-dd HH:mm:ss:SSS 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDateTimeMillis() {
        return format(DATETIME_MILLIS_FORMAT_STR);
    }

    /**
     * 用指定格式格式化当前时间并返回
     *
     * @param timePattern 指定的日期格式
     *
     * @return 当前时间
     */
    public static String format(String timePattern) {
        return format(LocalDateTime.now(), timePattern);
    }

    /**
     * 按照指定的格式格式化指定时间
     *
     * @param localDateTime 指定的时间
     * @param timePattern 指定的格式
     *
     * @return 格式化后的串
     */
    public static String format(LocalDateTime localDateTime, String timePattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，默认支持yyyy-MM-dd HH:mm:ss格式，例如：2007-12-03T10:15:30
     *
     * @param text 日期时间字符串
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_STR);
        return parse(text, dateTimeFormatter);
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，格式支持日期时间、日期、时间
     *
     * @param text 日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        if (null == text) {
            return null;
        }
        if (null == formatter) {
            return LocalDateTime.parse(text);
        }

        return of(formatter.parse(text));
    }

    /**
     * 获取当前的年份
     *
     * @return 当前年份
     */
    public int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * 获取当前月份是一年中的第几个月，值为 1 到 12 之间
     *
     * @return 当前月份
     */
    public int getCurrentMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    /**
     * 获取当前月份枚举
     *
     * @return 当前月份的枚举
     */
    public Month getCurrentMonthEnum() {
        return LocalDateTime.now().getMonth();
    }

    /**
     * 获取当前是一个月中的第几天，值在 1 到 31 之间
     *
     * @return 一个月中的第几天
     */
    public int getCurrentDayOfMonth() {
        return LocalDateTime.now().getDayOfMonth();
    }

    /**
     * 获取当前是一天中的第几个小时，值为 0 到 23 之间
     *
     * @return 当前小时数
     */
    public int getCurrentHour() {
        return LocalDateTime.now().getHour();
    }

    /**
     * 获取当前分钟数，值为 0 到 59 之间
     *
     * @return 当前分钟数
     */
    public int getCurrentMinute() {
        return LocalDateTime.now().getMinute();
    }

    /**
     * 获取当前秒数，值为 0 到 59 之间
     *
     * @return 当前秒数
     */
    public int getCurrentSecond() {
        return LocalDateTime.now().getSecond();
    }

    /**
     * 获取当前是一年中的第几天，值在 1 到 366 之间
     *
     * @return 一年中的第几天
     */
    public int getCurrentDayOfYear() {
        return LocalDateTime.now().getDayOfYear();
    }

    /**
     * 获取当前是一周中的第几天，周一为 1，周日为 7
     *
     * @return 一周中的第几天
     */
    public int getCurrentDayOfWeek() {
        return LocalDateTime.now().getDayOfWeek().getValue();
    }

    /**
     * 获取当前是周几
     *
     * @return 当前是星期几
     */
    public String getCurrentWeekDay() {
        return LocalDateTime.now().getDayOfWeek().name();
    }

    /**
     * 获取当前的星期枚举
     *
     * @return 星期枚举
     */
    public DayOfWeek getCurrentWeekDayEnum() {
        return LocalDateTime.now().getDayOfWeek();
    }

    /**
     * 获取指定月份有多少天
     *
     * @return 天数
     */
    public static int getDaysOfMonth(LocalDateTime localDateTime) {
        YearMonth yearMonth = YearMonth.of(localDateTime.getYear(), localDateTime.getMonth());
        return yearMonth.lengthOfMonth();
    }

    /**
     * 增减指定的年数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeYearsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.YEARS, oldDateTime);
    }

    /**
     * 增减指定的月数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeMonthsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.MONTHS, oldDateTime);
    }

    /**
     * 增减指定的天数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeDaysOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.DAYS, oldDateTime);
    }

    /**
     * 增减指定的小时数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeHoursOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.HOURS, oldDateTime);
    }

    /**
     * 增减指定的分钟数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeMinutesOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.MINUTES, oldDateTime);
    }

    /**
     * 增减指定的秒数
     *
     * @param delta 变化量（可以为负）
     * @param oldDateTime 原时间
     *
     * @return 变化后的时间
     */
    public static LocalDateTime changeSecondsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.SECONDS, oldDateTime);
    }

    /**
     * 增、减指定的时间单元，delta为正则增、为负则减
     *
     * @param delta 变化量
     * @param unit 时间单元
     * @param oldDateTime 原时间，在该基础上进行改变
     *
     * @return 改变后的时间
     */
    public static LocalDateTime changeOn(int delta, ChronoUnit unit, LocalDateTime oldDateTime) {
        return oldDateTime.plus(delta, unit);
    }

    /**
     * 获取两个时间相差的年数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceYears(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.YEARS);
    }

    /**
     * 获取两个时间相差的月数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceMonths(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.MONTHS);
    }

    /**
     * 获取两个时间相差的天数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceDays(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.DAYS);
    }

    /**
     * 获取两个时间相差的小时数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceHours(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.HOURS);
    }

    /**
     * 获取两个时间相差的分钟数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceMinutes(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.MINUTES);
    }

    /**
     * 获取两个时间相差的秒数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     *
     * @return 差值
     */
    public static long getDifferenceSeconds(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.SECONDS);
    }

    /**
     * 计算两个时间相差的时间单元数
     *
     * @param older 较早的时间
     * @param later 较晚的时间
     * @param timeUnit 指定时间单元
     *
     * @return 差值
     *
     * @see ChronoUnit
     */
    public static long getDifferenceTimeUnits(LocalDateTime older, LocalDateTime later, ChronoUnit timeUnit) {
        return older.until(later, timeUnit);
    }

    /**
     * 用指定的模式创建时间
     *
     * @param timeStr 表示时间的字符串
     * @param pattern 时间模式
     * @return 创建的时间
     */
    public static LocalDateTime parse(String timeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, formatter);
    }
}
