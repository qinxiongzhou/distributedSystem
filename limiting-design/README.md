# limiting-design 限流设计
## 思维导图
![limiting-design.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/limiting-design.png)

## 限流的实现方式
### 计数器方式
维护一个计数器Counter，当一个请求来时，做加1操作，当一个请求处理完成后，做减1操作。如果这个Counter大于某个数了（我们设定的限流阈值），拒绝请求。     

这个算法简单粗暴。   

#### 如：`counter`工程    

### 队列算法


### 漏斗算法Leaky Bucket
摘抄一下百科上的定义：    
漏斗算法(Leaky Bucket)是网络世界中流量整形（Traffic Shaping）或速率限制（Rate Limiting）时经常使用的一种算法，它的主要目的是控制数据注入到网络的速率，平滑网络上的突发流量。漏桶算法提供了一种机制，通过它，突发流量可以被整形以便为网络提供一个稳定的流量。漏桶可以看作是一个带有常量服务时间的单服务器队列，如果漏桶（包缓存）溢出，那么数据包会被丢弃。    
    
下图是漏斗算法的示意图：      
![leaky-bucket1.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/leaky-bucket1.png)    

我们可以看到，就像一个漏斗一样，进来的水量就好像访问流量一样，而出去的水量就像是我们系统处理请求一样。当访问流量过大时，这个漏斗就会积水，如果水太多就会溢出。           

![leaky-bucket2.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/leaky-bucket2.png)    
我们可以看到，漏斗算法其实就是请求中加入一个队列来做限流，让Processor可以匀速处理请求。

### 令牌桶算法 Token Bucket
关于令牌桶算法，主要时有一个中间人，在一个桶内按照一定的速率放入一些token，然后处理程序请求时，需要拿到token，才能处理；如果拿不到，则不处理。    
![token-bucket.png](https://github.com/qinxiongzhou/distributedSystem/blob/master/limiting-design/token-bucket.png)     
从理论上来说，令牌桶算法和漏斗算法不一样的是，漏斗算法中，处理请求是以一个常量和恒定的速度处理的，而令牌桶算法则是在流量小的时候“攒钱”，流量大的时候，可以快速处理。    

然而我们可能会问，Processor的处理速度因为有队列的存在，所以其总是能以最大处理能力来处理请求，这也是我们所希望的方式。因此，令牌桶和漏斗都是受制于Processor的最大处理能力。无论令牌桶李有多少令牌，也无论队列中还有多少请求。总之，Processor在大流量来临时总是按照自己最大的处理能力来处理的。    

但是，如果我们的Processor只是一个非常简单的任务分配器，比如像nginx、zuul这样的基本没有什么业务逻辑的网关，那么它的处理速度一定很快，不会有什么瓶颈，而其用来把请求转发给后端服务，那么在这种情况下，这两个算法就有不一样的情况了。   

####  漏斗算法会以一个稳定的速度转发，而令牌桶算法平时流量不大时在“攒钱”，流量大时，可以一次发出队列里所有请求，而后就收到令牌桶的流控限制。    

另外，令牌桶还可以做成第三方的一个服务，这样可以在分布式的系统中对全局进行流控，这也是一个很好的方式。



### 基于响应时间的动态限流
