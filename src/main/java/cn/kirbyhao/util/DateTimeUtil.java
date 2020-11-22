package cn.kirbyhao.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 时间与日期通用处理工具类，均采用java.time包，线程安全
 *
 * @author Nestist KirbyHao
 * Created At 2020-11-22
 */
public class DateTimeUtil {

    /**
     * 精确到日的日期格式
     */
    private static final String DATE_FORMAT_STR = "yyyy-MM-dd";

    /**
     * 精确到秒的日期格式
     */
    public static final String DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的日期格式
     */
    public static final String DATETIME_MILLIS_FORMAT_STR = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 当前日期，不包含时分秒
     */
    public static final LocalDate currentDate = LocalDate.now();

    /**
     * 当前日期时间
     */
    public static final LocalDateTime currentDateTime = LocalDateTime.now();

    /**
     * 获取当前时间。用 yyyy-MM-dd 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDate() {
        return getCurrentTimeByPattern(DATE_FORMAT_STR);
    }

    /**
     * 获取当前时间。用 yyyy-MM-dd HH:mm:ss 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDateTime() {
        return getCurrentTimeByPattern(DATETIME_FORMAT_STR);
    }

    /**
     * 获取当前时间。用 yyyy-MM-dd HH:mm:ss:SSS 格式返回
     *
     * @return 当前时间
     */
    public static String getCurrentDateTimeMillis() {
        return getCurrentTimeByPattern(DATETIME_MILLIS_FORMAT_STR);
    }

    /**
     * 获取当前的年份
     *
     * @return 当前年份
     */
    public int getCurrentYear() {
        return currentDateTime.getYear();
    }

    /**
     * 获取当前月份是一年中的第几个月，值为 1 到 12 之间
     *
     * @return 当前月份
     */
    public int getCurrentMonth() {
        return currentDateTime.getMonthValue();
    }

    /**
     * 获取当前月份枚举
     *
     * @return 当前月份的枚举
     */
    public Month getCurrentMonthEnum() {
        return currentDateTime.getMonth();
    }

    /**
     * 获取当前是一个月中的第几天，值在 1 到 31 之间
     *
     * @return 一个月中的第几天
     */
    public int getCurrentDayOfMonth() {
        return currentDateTime.getDayOfMonth();
    }

    /**
     * 获取当前是一天中的第几个小时，值为 0 到 23 之间
     *
     * @return 当前小时数
     */
    public int getCurrentHour() {
        return currentDateTime.getHour();
    }

    /**
     * 获取当前分钟数，值为 0 到 59 之间
     *
     * @return 当前分钟数
     */
    public int getCurrentMinute() {
        return currentDateTime.getMinute();
    }

    /**
     * 获取当前秒数，值为 0 到 59 之间
     *
     * @return 当前秒数
     */
    public int getCurrentSecond() {
        return currentDateTime.getSecond();
    }

    /**
     * 获取当前是一年中的第几天，值在 1 到 366 之间
     *
     * @return 一年中的第几天
     */
    public int getCurrentDayOfYear() {
        return currentDateTime.getDayOfYear();
    }

    /**
     * 获取当前是一周中的第几天，周一为 1，周日为 7
     *
     * @return 一周中的第几天
     */
    public int getCurrentDayOfWeek() {
        return currentDateTime.getDayOfWeek().getValue();
    }

    /**
     * 获取当前是周几
     *
     * @return 当前是星期几
     */
    public String getCurrentWeekDay() {
        return currentDateTime.getDayOfWeek().name();
    }

    /**
     * 获取当前的星期枚举
     *
     * @return 星期枚举
     */
    public DayOfWeek getCurrentWeekDayEnum() {
        return currentDateTime.getDayOfWeek();
    }

    /**
     * 私有，用指定格式格式化当前时间并返回
     *
     * @param timePattern 指定的日期格式
     * @return 当前时间
     */
    public static String getCurrentTimeByPattern(String timePattern) {
        return format(currentDateTime, timePattern);
    }

    /**
     * 按照指定的格式格式化指定时间
     *
     * @param localDateTime 指定的时间
     * @param timePattern   指定的格式
     * @return 格式化后的串
     */
    public static String format(LocalDateTime localDateTime, String timePattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
        return localDateTime.format(dateTimeFormatter);
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
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeYearsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.YEARS, oldDateTime);
    }

    /**
     * 增减指定的月数
     *
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeMonthsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.MONTHS, oldDateTime);
    }

    /**
     * 增减指定的天数
     *
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeDaysOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.DAYS, oldDateTime);
    }

    /**
     * 增减指定的小时数
     *
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeHoursOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.HOURS, oldDateTime);
    }

    /**
     * 增减指定的分钟数
     *
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeMinutesOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.MINUTES, oldDateTime);
    }

    /**
     * 增减指定的秒数
     *
     * @param delta       变化量（可以为负）
     * @param oldDateTime 原时间
     * @return 变化后的时间
     */
    public static LocalDateTime changeSecondsOn(int delta, LocalDateTime oldDateTime) {
        return changeOn(delta, ChronoUnit.SECONDS, oldDateTime);
    }

    /**
     * 增、减指定的时间单元，delta为正则增、为负则减
     *
     * @param delta       变化量
     * @param unit        时间单元
     * @param oldDateTime 原时间，在该基础上进行改变
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
     * @return 差值
     */
    public static long getDifferenceSeconds(LocalDateTime older, LocalDateTime later) {
        return getDifferenceTimeUnits(older, later, ChronoUnit.SECONDS);
    }

    /**
     * 计算两个时间相差的时间单元数
     *
     * @param older    较早的时间
     * @param later    较晚的时间
     * @param timeUnit 指定时间单元
     * @return 差值
     * @see ChronoUnit
     */
    public static long getDifferenceTimeUnits(LocalDateTime older, LocalDateTime later, ChronoUnit timeUnit) {
        return older.until(later, timeUnit);
    }
}
