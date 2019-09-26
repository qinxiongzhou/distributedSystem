package com.ryan.tokenbucket.controller;

import com.ryan.tokenbucket.limit.LimitTokenBucket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Controller
 * @Description: 令牌桶限流控制器
 * @Author ryan
 * @Date 2019/9/26
 * @Version V1.0
 **/

@RestController
public class Controller {
    @RequestMapping("/limit")
    @LimitTokenBucket(concurrentNum = "0")
    public String limit(){
        return "limit token-bucket";
    }
}
