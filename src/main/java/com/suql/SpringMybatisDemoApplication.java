package com.suql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication 等同于默认的属性的 @Configuration, @EnableAutoConfiguration and @ComponentScan。
//		-- 注意，@ComponentScan 不能凭空使用。
@SpringBootApplication
public class SpringMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisDemoApplication.class, args);
	}

}
