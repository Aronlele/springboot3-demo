package tk.huclele.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import tk.huclele.demo.annotations.OnlySpecifyValue;

import java.lang.reflect.Method;

/**
 * 校验检查输入的参数值是否为允许值列表中的.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/10/11 10:23
 */
public class OnlySpecifyValueValidator implements ConstraintValidator<OnlySpecifyValue, Object> {

    private Class<?> enumValue;

    private int[] intValues;

    private String[] strValues;

    /**
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(OnlySpecifyValue constraintAnnotation) {
        enumValue = constraintAnnotation.enumValue();
        intValues = constraintAnnotation.intValues();
        strValues = constraintAnnotation.strValues();
    }

    /**
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return result
     */
    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (enumValue.isEnum()) {
            Object[] objects = enumValue.getEnumConstants();
            for (Object obj : objects) {
                Method method = enumValue.getDeclaredMethod("getValue");
                String expectValue = String.valueOf(method.invoke(obj));
                if (expectValue.equals(String.valueOf(value))) {
                    return true;
                }
            }
        } else {
            if (value instanceof String) {
                for (String s : strValues) {
                    if (s.equals(value)) {
                        return true;
                    }
                }
            } else if (value instanceof Integer) {
                for (Integer s : intValues) {
                    if (s == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
