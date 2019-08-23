# limiting-design 限流设计
## 思维导图
![limiting-design.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/limiting-design.png)

## 限流的实现方式
### 计数器方式
维护一个计数器Counter，当一个请求来时，做加1操作，当一个请求处理完成后，做减1操作。如果这个Counter大于某个数了（我们设定的限流阈值），拒绝请求。    
如：<font color="#FF0000">counter</font>工程
### 队列算法


### 漏斗算法Leaky Bucket


### 令牌桶算法 Token Bucket


### 基于响应时间的动态限流
