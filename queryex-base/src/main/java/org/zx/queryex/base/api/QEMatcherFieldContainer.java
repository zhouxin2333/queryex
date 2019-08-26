package org.zx.queryex.base.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public class QEMatcherFieldContainer {

    private static final Map<Class, Field[]> fieldsMap = new HashMap<>();
    private static final Predicate<Field> notStaticFieldPredicate = field -> !Modifier.isStatic(field.getModifiers());
    private static final Function<Class, Predicate<Field>> containAnnotationFunction =
            annotationClass -> field -> field.isAnnotationPresent(annotationClass);

    public static Field[] getAllMatcherFields(Object obj){
        Class targetClass = obj.getClass();
        if (fieldsMap.containsKey(targetClass)){
            return fieldsMap.get(targetClass);
        }
        Field[] targetFields = QEUtils.getAllFieldsWithRoot(targetClass,
                                                            QEMatcherFieldContainer.notStaticFieldPredicate,
                                                            QEMatcherFieldContainer.containAnnotationFunction.apply(QEMatcherField.class));
        fieldsMap.put(targetClass, targetFields);
        return targetFields;
    }
}
