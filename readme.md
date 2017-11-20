logger starter

### 使用

               <dependency>
                    <groupId>com.yazuo.intelligent</groupId>
                    <artifactId>logger-starter</artifactId>
                    <version>${version}</version>
                </dependency>
                
> 需要日志的方法必须加上@ApiOperation注解

- 打印格式

error  :  `耗时-{}毫秒 代码:{} 异常信息:{} 方法:{}[{}] 参数:{}         `
info   :  `耗时-{}毫秒 代码:{} 方法:{}[{}] 参数:{} 返回值:{}`       

## 拓展

- info级别实现InfoLogger后注入
- error级别实现ErrorLogger后注入
- 过滤不需要打印的属性实现LoggerParamsFilter后注入或继承HttpObjectFilter

### @LoggerFilter注解 

> 标注在方法上

- value 过滤指定类型的值
- keys 过滤指定字段名的值
```java
     @LoggerFilter(keys = {"list"},value = {Integer.class,String.class})

```
