package ac.cn.saya.lab.financial;

import ac.cn.saya.lab.api.tools.CurrentLineInfo;
import ac.cn.saya.lab.api.tools.Log4jUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
@ComponentScan(basePackages = {"ac.cn.saya.lab.financial","ac.cn.saya.lab.api.bean"})
@EnableDiscoveryClient
public class FinancialApplication {

    private static Logger logger = LoggerFactory.getLogger(FinancialApplication.class);

    public static void main(String[] args) {
        try {
            /// SpringApplication.run(FinancialApplication.class, args);
            SpringApplication springApplication = new SpringApplication(FinancialApplication.class);
            // 禁止命令行设置参数
            springApplication.setAddCommandLineProperties(false);
            springApplication.run(args);
            //项目启动完成打印项目名
            logger.info("实验室中心-财政金融服务已经启动 ... ");
        } catch (Exception e) {
            logger.error("实验室中心-财政金融服务启动失败:", Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
    }

}
