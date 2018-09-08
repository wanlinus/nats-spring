# nats-spring
### Configuring a NATS client in Spring
This project provides support for using Spring Boot with [NATS](https://nats.io/).

# Quick Start
clone this project 
```bash
git clone https://github.com/wanlinus/nats-spring.git
cd nats-spring
mvn clean install
```

```xml
<dependency>
  <groupId>cn.wanlinus</groupId>
  <artifactId>nats-spring</artifactId>
  <version>1.0.0.RELEASE</version>
</dependency>
```
The Java SE 8 or higher is recommended to build the project.

## Basic Usage

```java
@SpringBootApplication
@EnableNats
public class NatsDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(NatsDemo2Application.class, args);
    }
}
```

```java

@Component
public class Foo{

  @NatsSubscribe("haha")
  public void message(Message message) {
      System.out.println(message.getSubject() + " : " + new String(message.getData()));
  }
}
```

# Resources

For more information, please visit the Nats Spring website at:
[Reference Manual](https://github.com/wanlinus/nats-spring)


# License

Nats-Spring is released under the terms of the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).


