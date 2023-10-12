package tk.huclele.demo.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tk.huclele.demo.validator.OnlySpecifyValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 注解元素只允许预定义值.
 *
 * @author xuelele
 * @version 1.0
 * @since 2023/10/11 10:16
 */
@Documented
@Constraint(validatedBy = {OnlySpecifyValueValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(OnlySpecifyValue.List.class)
public @interface OnlySpecifyValue {

    String message() default "必须为允许的值列表";

    String[] strValues() default {};

    int[] intValues() default {};

    Class<?> enumValue() default Class.class;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@code @OnlySpecifyValue} constraints on the same element.
     *
     * @see OnlySpecifyValue
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        OnlySpecifyValue[] value();
    }
}
