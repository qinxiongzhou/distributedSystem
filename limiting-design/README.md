# limiting-design 限流设计
## 思维导图
![limiting-design.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/limiting-design.png)

## 限流的实现方式
### 计数器方式
维护一个计数器Counter，当一个请求来时，做加1操作，当一个请求处理完成后，做减1操作。如果这个Counter大于某个数了（我们设定的限流阈值），拒绝请求。     

这个算法简单粗暴。   

如：`counter`工程    

### 队列算法


### 漏斗算法Leaky Bucket
漏桶算法(Leaky Bucket)是网络世界中流量整形（Traffic Shaping）或速率限制（Rate Limiting）时经常使用的一种算法，它的主要目的是控制数据注入到网络的速率，平滑网络上的突发流量。漏桶算法提供了一种机制，通过它，突发流量可以被整形以便为网络提供一个稳定的流量。漏桶可以看作是一个带有常量服务时间的单服务器队列，如果漏桶（包缓存）溢出，那么数据包会被丢弃

### 令牌桶算法 Token Bucket


### 基于响应时间的动态限流
