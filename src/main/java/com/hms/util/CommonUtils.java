package com.hms.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hms.base.PageResult;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class CommonUtils {

    public static final String ATTR_VALUE_CHECK_ERROR = "入参属性值校验异常!";
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static String removeLastWord(String word) {
        if (word.length() > 0) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    public static Integer int2IntegerIngore(int num, int ignore) {

        if (num == ignore) {
            return null;
        } else {
            return num;
        }
    }

    public static int null2Zero(Integer num) {

        if (num == null) {
            return 0;
        }

        return num;
    }

    // 非int类型的数据会转成0
    public static Integer[] string2IntArr(String word, String split) {

        if (StringUtils.isEmpty(word)) {
            return null;
        }

        String[] strArr = word.split(split);
        Integer[] result = new Integer[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            result[i] = NumberUtils.toInt(strArr[i]);
        }

        return result;
    }

    public static String getRemoteIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.warn("ip: " + ip);
        return ip;
    }

    /**
     * 通过反射获取fields然后进行赋值; 有继承关系的field无法赋值
     *
     * @param sour
     * @param dest
     * @throws Exception
     */
    public static void doTrans(Object sour, Object dest) throws Exception {

        if (sour == null) {
            dest = null;
            return;
        }

        Class<?> sourClass = sour.getClass();
        Class<?> destClass = dest.getClass();

        for (Field modelField : sourClass.getDeclaredFields()) {

            String fieldName = modelField.getName();
            Object value;

            if (fieldName.equals("serialVersionUID")) {
                continue;
            }

            try {
                PropertyDescriptor pdModel = new PropertyDescriptor(fieldName, sourClass);
                value = pdModel.getReadMethod().invoke(sour);
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug(
                        sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e
                            .getMessage());
                }
                continue;
            }

            if (value == null) {
                continue;
            }

            try {
                PropertyDescriptor pdDto = new PropertyDescriptor(fieldName, destClass);
                pdDto.getWriteMethod().invoke(dest, value);
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug(
                        sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e
                            .getMessage());
                }
                continue;
            }
        }
    }

    /**
     * 通过反射获取所有set为前缀的方法,然后截断set字符串,余下的字符串作为field进行赋值; 优先使用doTrans方法
     *
     * @param sour
     * @param dest
     * @throws Exception
     */
    public static void doTransAll(Object sour, Object dest) throws Exception {

        if (sour == null) {
            dest = null;
            return;
        }

        Class<?> sourClass = sour.getClass();
        Class<?> destClass = dest.getClass();

        for (Method method : sourClass.getMethods()) {

            String methodName = method.getName();

            if (!methodName.startsWith("set")) {
                continue;
            }

            String fieldName = methodName.substring(3);
            Object value;

            if (fieldName.equals("serialVersionUID")) {
                continue;
            }

            try {
                PropertyDescriptor pdModel = new PropertyDescriptor(fieldName, sourClass);
                value = pdModel.getReadMethod().invoke(sour);
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug(
                        sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e
                            .getMessage());
                }
                continue;
            }

            if (value == null) {
                continue;
            }

            try {
                PropertyDescriptor pdDto = new PropertyDescriptor(fieldName, destClass);
                pdDto.getWriteMethod().invoke(dest, value);
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug(
                        sour.getClass().getName() + " - " + dest.getClass().getName() + " - " + fieldName + " - " + e
                            .getMessage());
                }
                continue;
            }
        }
    }

    public static String strTrim(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str.trim();
    }

    public static Number numTrim(Number num) {
        if (num == null) {
            return 0;
        }
        return num;
    }

    public static <T extends Number> T numTrim1(T num, T key) {
        return (num != null) ? num : key;
    }

    public static Long parseStr2Long(String str, String attrName) {
        Long lon = null;
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            str = str.trim();
            lon = Long.parseLong(str);
        } catch (Exception e) {
            throw new RuntimeException(ATTR_VALUE_CHECK_ERROR + "attrName=" + attrName + ",attrValue=" + str);
        }
        return lon;
    }

    public static Long parseStr2Long(String str) {

        if (StringUtils.isBlank(str)) {
            return null;
        }

        return NumberUtils.toLong(str);
    }

    public static Integer parseStr2Integer(String str, String attrName) {
        Integer in = null;
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            str = str.trim();
            in = Integer.parseInt(str);
        } catch (Exception e) {
            throw new RuntimeException(ATTR_VALUE_CHECK_ERROR + "attrName=" + attrName + ",attrValue=" + str);
        }
        return in;
    }

    public static String parseLong2Str(Long lon) {
        if (lon == null) {
            return "";
        }
        return Long.toString(lon);
    }

    public static String parseDouble2Str(Double dou) {
        if (dou == null) {
            return "";
        }
        return Double.toString(dou);
    }

    public static String parseInt2Str(Integer in) {
        if (in == null) {
            return "";
        }
        return Integer.toString(in);
    }

    public static Long parseInt2Long(Integer in) {
        if (in == null) {
            return Long.valueOf(0);
        }
        return Long.valueOf(in);
    }

    /**
     * PageResult<DB>转换成PageResult<VO>
     */
    public static <D, V> PageResult<V> dbToVo(PageResult<D> dbRes, Class<V> clz, CallBack<D, V> callBack)
        throws Exception {

        PageResult<V> voRes = new PageResult<V>();
        CommonUtils.doTrans(dbRes, voRes);

        List<V> vos = Lists.newArrayList();
        for (D db : dbRes.getList()) {
            V vo = clz.newInstance();
            CommonUtils.doTrans(db, vo);
            callBack.execute(db, vo);
            vos.add(vo);
        }
        voRes.setList(vos);

        return voRes;
    }

    /**
     * PageResult<DB>转换成PageResult<VO>
     */
    public static <D, V> PageResult<V> dbToVo(PageResult<D> dbRes, Class<V> clz) throws Exception {

        PageResult<V> voRes = new PageResult<V>();
        CommonUtils.doTrans(dbRes, voRes);

        List<V> vos = Lists.newArrayList();
        for (D db : dbRes.getList()) {
            V vo = clz.newInstance();
            CommonUtils.doTrans(db, vo);
            vos.add(vo);
        }
        voRes.setList(vos);

        return voRes;
    }

    /**
     * List<DB>转换成List<VO>
     */
    public static <D, V> List<V> dbToVo(List<D> dbList, Class<V> clz, CallBack<D, V> callBack) throws Exception {

        List<V> voList = Lists.newArrayList();
        for (D db : dbList) {
            V vo = clz.newInstance();
            CommonUtils.doTrans(db, vo);
            callBack.execute(db, vo);
            voList.add(vo);
        }

        return voList;
    }

    /**
     * List<DB>转换成List<VO>
     */
    public static <D, V> List<V> dbToVo(List<D> dbList, Class<V> clz) throws Exception {

        List<V> voList = Lists.newArrayList();
        for (D db : dbList) {
            V vo = clz.newInstance();
            CommonUtils.doTrans(db, vo);
            voList.add(vo);
        }

        return voList;
    }

    public static String getCookieValueByName(HttpServletRequest request, String name, String defaultValue) {

        Cookie[] cookies = request.getCookies();
        if (!ArrayUtils.isEmpty(cookies)) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }

        return defaultValue;
    }

    public static <D, V> V dbToVo(D db, Class<V> clz) throws Exception {

        V vo = clz.newInstance();
        CommonUtils.doTrans(db, vo);

        return vo;
    }

    public static <D, V> V dbToVo(D db, Class<V> clz, CallBack<D, V> callBack) throws Exception {

        V vo = clz.newInstance();
        CommonUtils.doTrans(db, vo);
        callBack.execute(db, vo);
        return vo;
    }

    public static String setToString(Set<String> set) {
        return set.toString().substring(1, set.toString().length() - 1);
    }

    /**
     * PageResult<DB>转换成PageResult<VO> 如果db有子类，则也会在子类中查找字段
     */
    public static <D, V> PageResult<V> dbWithSubClassToVo(PageResult<D> dbRes, Class<V> clz, CallBack<D, V> callBack)
        throws Exception {

        PageResult<V> voRes = new PageResult<V>();
        CommonUtils.doTrans(dbRes, voRes);

        List<V> vos = Lists.newArrayList();
        for (D db : dbRes.getList()) {
            V vo = clz.newInstance();
            CommonUtils.doTransAll(db, vo);
            callBack.execute(db, vo);
            vos.add(vo);
        }
        voRes.setList(vos);

        return voRes;
    }

    public static <T> List<T> setToList(Set<T> set) {

        if (set == null) {
            return null;
        }

        List<T> list = Lists.newArrayList();
        for (T t : set) {
            list.add(t);
        }

        return list;
    }

    public static <T> Set<T> listToSet(List<T> list) {

        if (list == null) {
            return null;
        }

        Set<T> set = Sets.newHashSet();
        for (T t : list) {
            set.add(t);
        }

        return set;
    }

    /**
     * 字符串转成List<T>
     *
     * @param source 以,隔开的字符串
     * @param clz    要转换的对象Class
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> stringToList(String source, Class<T> clz) {

        if (StringUtils.isBlank(source)) {
            return null;
        }

        List<T> list = Lists.newArrayList();
        for (String s : StringUtils.split(source, ",")) {
            if (clz == String.class) {
                list.add((T) s);
            } else if (clz == Long.class) {
                list.add((T) Long.valueOf(NumberUtils.toLong(s)));
            } else if (clz == Integer.class) {
                list.add((T) Integer.valueOf(NumberUtils.toInt(s)));
            } else if (clz == Double.class) {
                list.add((T) Double.valueOf(NumberUtils.toDouble(s)));
            }

        }
        return list;
    }

    /**
     * 字符串转成Set<T>
     *
     * @param source 以,隔开的字符串
     * @param clz    要转换的对象Class
     * @param <T>
     * @return
     */
    public static <T> Set<T> stringToSet(String source, Class<T> clz) {

        if (StringUtils.isBlank(source)) {
            return null;
        }

        Set<T> set = Sets.newHashSet();
        for (String s : StringUtils.split(source, ",")) {
            if (clz == String.class) {
                set.add((T) s);
            } else if (clz == Long.class) {
                set.add((T) Long.valueOf(NumberUtils.toLong(s)));
            } else if (clz == Integer.class) {
                set.add((T) Integer.valueOf(NumberUtils.toInt(s)));
            } else if (clz == Double.class) {
                set.add((T) Double.valueOf(NumberUtils.toDouble(s)));
            }

        }
        return set;
    }

    public static String doubleToString(Double dValue) {
        if (dValue == null) {
            return "";
        }
        String strValue = Double.toString(dValue);
        if (strValue.indexOf(".") > 0) {
            strValue = strValue.replaceAll("0+?$", "");// 去掉后面无用的零
            strValue = strValue.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
        }
        return strValue;
    }

    public interface CallBack<D, V> {
        public void execute(D db, V vo);
    }

    public static Long getInnerOwnerId(){
        //库内多商家场景，不需要传入OWNER_ID的场景
        return null;
    }
}
