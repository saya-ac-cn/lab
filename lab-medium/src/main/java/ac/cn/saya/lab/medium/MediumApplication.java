package ac.cn.saya.lab.medium;

import ac.cn.saya.lab.api.tools.CurrentLineInfo;
import ac.cn.saya.lab.api.tools.Log4jUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @描述 服务启动入口
 * @参数
 * @返回值
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2020-02-29
 * @修改人和其它信息
 */

//// 移除 @SpringBootApplication 用 @ComponentScan、@Configuration及 @EnableAutoConfiguration 来替代
//// @SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ac.cn.saya.lab.medium","ac.cn.saya.lab.api.bean"})
@EnableDiscoveryClient
// 开启定时任务
@EnableScheduling
public class MediumApplication {

    private static Logger logger = LoggerFactory.getLogger(MediumApplication.class);

    public static void main(String[] args) {
        try {
            /// SpringApplication.run(MediumApplication.class, args);
            SpringApplication springApplication = new SpringApplication(MediumApplication.class);
            // 禁止命令行设置参数
            springApplication.setAddCommandLineProperties(false);
            springApplication.run(args);
            //项目启动完成打印项目名
            logger.info("实验室中心-媒体服务已经启动 ... ");
        } catch (Exception e) {
            logger.error("实验室中心-媒体服务启动失败:", Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
    }

}
