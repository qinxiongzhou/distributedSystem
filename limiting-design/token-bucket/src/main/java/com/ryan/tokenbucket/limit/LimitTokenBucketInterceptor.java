package com.ryan.tokenbucket.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LimitTokenBucketInterceptor
 * @Description: TODO
 * @Author grid
 * @Date 2019/9/26
 * @Version V1.0
 **/
@Component
@Aspect
public class LimitTokenBucketInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LimitTokenBucketInterceptor.class);
    private Gson gson = new Gson();
    private ConcurrentHashMap<String,RateLimiter> limitHashMap = new ConcurrentHashMap<String, RateLimiter>();

    @Pointcut("@annotation(com.ryan.tokenbucket.limit.LimitTokenBucket)")
    public void limitTokenBucketPointcut(){}

    @Around("limitTokenBucketPointcut()")
    public Object limitCounterPointcutInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        //key为类名+方法名
        String key = method.getDeclaringClass().getName()+"."+method.getName();

        LimitTokenBucket limitTokenBucket = method.getAnnotation(LimitTokenBucket.class);
        Integer concurrentNum = Integer.parseInt(limitTokenBucket.concurrentNum());

        RateLimiter rateLimiter = limitHashMap.putIfAbsent(key, RateLimiter.create(concurrentNum));
        //TODO...

        return "limitTokenBucketPointcut： "+concurrentNum;
    }
}
