package com.ryan.counter.limit;

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
import java.util.concurrent.atomic.AtomicInteger;

/**
* @Description:    计数器限流类
* @Author:         zhouqinxiong
* @CreateDate:     2019/8/23 9:10
* @Version:        1.0
*/
@Component
@Aspect
public class LimitCounterInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LimitCounterInterceptor.class);
    private Gson gson = new Gson();

    private ConcurrentHashMap<String,AtomicInteger> limitHashMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.ryan.counter.limit.LimitCounter)")
    public void limitCounterPointcut(){
    }

    @Around("limitCounterPointcut()")
    public Object limitCounterPointcutInterceptor(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        //key为类名+方法名
        String key = method.getDeclaringClass().getName()+method.getName();

        LimitCounter limitCounter = method.getAnnotation(LimitCounter.class);
        Integer concurrentNum = Integer.parseInt(limitCounter.concurrentNum());

        AtomicInteger atomicInteger = limitHashMap.putIfAbsent(key,new AtomicInteger(1));

        if(atomicInteger == null){
            result = pjp.proceed();
            atomicInteger = limitHashMap.get(key);
            atomicInteger.decrementAndGet();
        }else {
            atomicInteger = limitHashMap.get(key);
            if(atomicInteger.get() < concurrentNum) {
                atomicInteger.incrementAndGet();
                result = pjp.proceed();
                atomicInteger.decrementAndGet();
            }else{
                ResultObject resultObject = new ResultObject("00500","当前并发请求数:"+atomicInteger.get()+",该请求被限流");
                logger.info(gson.toJson(resultObject));
                result = gson.toJson(resultObject);
            }
        }
        return result;
    }


}
