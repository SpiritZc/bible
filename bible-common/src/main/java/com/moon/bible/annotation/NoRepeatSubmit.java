package com.moon.bible.annotation;

import java.lang.annotation.*;

/**  
 * @ClassName: NoRepeatSubmit
 * @Description: 防止重复提交注解
 * @author caiyang
 * @date 2020-06-01 08:46:16 
*/ 
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {

    /**  
     * @Title: expire
     * @Description: 几秒之内无法重复提交，默认5秒
     * @return
     * @author caiyang
     * @date 2020-06-01 08:46:36 
     */ 
    int expire() default 2;
}
