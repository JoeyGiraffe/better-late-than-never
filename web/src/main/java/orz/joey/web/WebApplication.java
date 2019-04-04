package orz.joey.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//启动时会默认扫描主类当前包及子包
//指定扫描的实体类所在包名，自动创建数据表
@ComponentScan(basePackages = "orz.joey")
@EntityScan(basePackages = {"orz.joey.repository.entity"})
@EnableJpaRepositories(basePackages = "orz.joey.repository")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
