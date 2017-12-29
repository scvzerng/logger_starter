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

### 注解个性化

> 日志切面注解和过滤日志注解支持个性化只要属性名一致不缺失

### example

```java
@LoggerFilter(keys = {"list"},value = {Integer.class,String.class})
```
可被

```java
@LogTag(keys = {"list"},value = {Integer.class,String.class})
```
替换

```yaml
log:
  log-annotation: ApiOperation.class
  filter-annotation: LoggerFilter.class
```

