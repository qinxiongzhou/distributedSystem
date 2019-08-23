package com.ryan.counter.controller;

import com.ryan.counter.limit.LimitCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description:    计数器限流控制器
* @Author:         zhouqinxiong
* @CreateDate:     2019/8/23 9:09
* @Version:        1.0
*/
@RestController
public class Controller {
    @GetMapping("limit")
    @LimitCounter(concurrentNum="0")
    public String limit(){
        System.out.println("limit interface");
        return "limit interface";
    }
}
