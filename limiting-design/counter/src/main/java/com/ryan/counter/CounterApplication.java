package com.ryan.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Description:    计数器限流例子
*                  维护一个计数器Counter，当一个请求来时，做加1操作，当一个请求处理完成后，做减1操作。如果这个Counter大于某个数了（我们设定的限流阈值），拒绝请求。
* @Author:         zhouqinxiong
* @CreateDate:     2019/8/23 9:22
* @Version:        1.0
*/
@SpringBootApplication
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);
    }

}
