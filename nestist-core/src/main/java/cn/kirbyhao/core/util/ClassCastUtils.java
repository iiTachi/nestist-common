package cn.kirbyhao.core.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 类型转换工具类
 *
 * @author Nestist KirbyHao
 * Created At 2020-11-26
 */
public class ClassCastUtils {

    /**
     * 将Object对象转换为指定的List对象，若Object不是列表，则返回null
     *
     * @param object    object对象，实质上是一个列表
     * @param clazz     列表中每一项的类型
     * @param listClass 指定的list类型
     * @param <T>       泛型
     * @return 转换后的指定的List列表对象
     * @throws IllegalAccessException 非法访问错误
     * @throws InstantiationException 创建实例错误
     */
    public static <T> List<T> castToList(Object object, Class<T> clazz, Class<? extends List> listClass)
            throws IllegalAccessException, InstantiationException {
        List<T> result = listClass.newInstance();
        if (object instanceof List<?>) {
            for (Object o : (List<?>) object) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * 将Object对象转换为List对象，若Object不是列表，则返回null
     * 默认转换为LinkedList类型
     *
     * @param object object对象，实质上是一个列表
     * @param clazz  列表中每一项的类型
     * @param <T>    泛型
     * @return 转换后的List列表对象
     */
    public static <T> List<T> castToList(Object object, Class<T> clazz) {
        List<T> result = new LinkedList<>();
        if (object instanceof List<?>) {
            for (Object o : (List<?>) object) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * 将Object转换为指定的Set
     *
     * @param object   object对象，实质上是一个集合
     * @param clazz    集合中每一项的类型
     * @param setClass 指定的集合类型
     * @param <T>      泛型
     * @return 转换后的指定Set集合对象
     * @throws IllegalAccessException 非法访问错误
     * @throws InstantiationException 创建实例错误
     */
    public static <T> Set<T> castToSet(Object object, Class<T> clazz, Class<? extends Set> setClass)
            throws IllegalAccessException, InstantiationException {
        // 采用指定的Set类
        Set<T> result = setClass.newInstance();
        if (object instanceof Set<?>) {
            for (Object o : (Set<?>) object) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * 将Object对象转换为List对象，若Object不是列表，则返回null
     * 默认转换为HashSet类型
     *
     * @param object object对象，实质上是一个列表
     * @param clazz  列表中每一项的类型
     * @param <T>    泛型
     * @return 转换后的Set集合对象
     */
    public static <T> Set<T> castToSet(Object object, Class<T> clazz) {
        Set<T> result = new HashSet<>();
        if (object instanceof Set<?>) {
            for (Object o : (Set<?>) object) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
