package com.ryan.counter.limit;

import java.lang.annotation.*;

/**
* @Description:    计数器限流注解
* @Author:         zhouqinxiong
* @CreateDate:     2019/8/23 9:10
* @Version:        1.0
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface LimitCounter {
    /**
     * @Description 并发数
     * @return
     */
    String concurrentNum();
}
