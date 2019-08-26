package org.zx.queryex.base.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public class QEUtils {

    public static Object getValueWithoutError(Object obj, String name) {
        Object value = null;
        try {
            Method method = obj.getClass().getMethod("get" + QEUtils.toFirstUpCase(name));
            value = method.invoke(obj);
        } catch (Exception e) {
        }
        return value;
    }

    public static Field[] getAllFieldsWithRoot(Class<?> clazz, Predicate<Field>... predicates) {
        List<Field> fieldList = new ArrayList<Field>();
        Field[] dFields = clazz.getDeclaredFields();
        if (QEUtils.isNotEmpty(dFields)) fieldList.addAll(Arrays.asList(dFields));

        Predicate<Field> fieldPredicate = null;
        if (QEUtils.isNotEmpty(predicates)){
            Predicate<Field> predicate = Arrays.stream(predicates).reduce(Predicate::and).get();
            fieldPredicate = predicate;
            fieldList = fieldList.stream().filter(field -> predicate.test(field)).collect(Collectors.toList());
        }

        // 若父类是Object，则直接返回当前Field列表
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == Object.class) return fieldList.toArray(new Field[fieldList.size()]);

        // 递归查询父类的field列表
        Field[] superFields = getAllFieldsWithRoot(superClass, fieldPredicate);
        if (QEUtils.isNotEmpty(superFields)) {
            List<Field> superFieldList = filterField(superFields, fieldList, fieldPredicate);
            if (QEUtils.isNotEmpty(superFieldList)){
                fieldList.addAll(superFieldList);
            }
        }

        Field[] result = new Field[fieldList.size()];
        fieldList.toArray(result);
        return result;
    }

    private static List<Field> filterField(Field[] superFields, List<Field> fieldList, Predicate<Field> predicate) {
        return Stream.of(superFields).filter(field -> !fieldList.contains(field))
                .filter(field -> predicate == null || predicate.test(field))
                .collect(Collectors.toList());
    }

    public static boolean isNotEmpty(final Object[] target) {
        return !QEUtils.isEmpty(target);
    }
    public static boolean isEmpty(final Object[] target) {
        return target == null || target.length <= 0;
    }
    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }
    public static boolean isNotEmpty(Collection c) {
        return !QEUtils.isEmpty(c);
    }
    public static <T> T findFirst(List<T> c){
        return QEUtils.isEmpty(c) ? null : c.get(0);
    }
    public static <T> T findLast(List<T> c){
        return QEUtils.isEmpty(c) ? null : c.get(c.size() - 1);
    }
    public static String toFirstUpCase(String str) {
        return toSomeCase(str, String::toUpperCase);
    }
    private static String toSomeCase(String str, Function<String, String> fun) {
        return fun.apply(str.substring(0, 1)) + str.substring(1, str.length());
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
