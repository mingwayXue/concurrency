package com.xue.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 标记为【线程安全】
 * Created by Mingway on 2019/3/21.
 */
@Target(ElementType.TYPE)   //类注解
@Retention(RetentionPolicy.SOURCE)  //表示不加入到编译里面，在此仅作标记使用
public @interface ThreadSafe {
    String value() default "";
}
