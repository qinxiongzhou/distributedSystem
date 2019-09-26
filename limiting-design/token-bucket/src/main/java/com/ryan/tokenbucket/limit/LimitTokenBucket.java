package com.ryan.tokenbucket.limit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface LimitTokenBucket {
    /**
     * @Description 并发数
     * @return
     */
    String concurrentNum();
}
