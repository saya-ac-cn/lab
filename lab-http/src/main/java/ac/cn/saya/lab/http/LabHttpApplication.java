package ac.cn.saya.lab.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//// @SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(value = {"ac.cn.saya.lab.http","ac.cn.saya.lab.financial"})
public class LabHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabHttpApplication.class, args);
    }

}
