/**
 * @author 51937
 * @date 2021年4月20日
 * @version V1.0
 */
package com.lzh.authenticationservice.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 51937
 * @date 2021年4月20日
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenCheck {
    String value() default "";
}
