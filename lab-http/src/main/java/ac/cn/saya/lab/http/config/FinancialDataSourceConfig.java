package ac.cn.saya.lab.http.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Title: FinancialDataSourceConfig
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-02 14:13
 * @Description: 核心数据库-数据源配置
 */

@Configuration
@MapperScan(basePackages = "ac.cn.saya.lab.financial.repository", sqlSessionTemplateRef = "financialSqlSessionTemplate")
public class FinancialDataSourceConfig {

    public static FinancialDataSourceConfig create() {
        return new FinancialDataSourceConfig();
    }

    /**
     * 创建财政数据库数据源
     *
     * @return
     */
    public DruidDataSource buildFinancialDataSource() {
        return new FinancialDataSourceWrapper();
    }

    /**
     * 主数据源: 如果在使用时,不特别指定Bean的名称,默认是使用主数据源操作(@Primary).
     *
     * @return
     * @Primary: 自动装配时当出现多个Bean时, 被注解为@Primary的Bean将作为首选者,否则将抛出异常 .
     * @ConfigurationProperties : 根据配置文件中prefix前缀的属性名称,批量注入属性值.
     */
    @Bean(name = "financialDataSource")
    public DataSource financialDataSource() {
        /// 第一种默认创建方式
        ///DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();

        /// 第二种手动创建方式
        DruidDataSource druidDataSource = FinancialDataSourceConfig.create().buildFinancialDataSource();
        System.out.println("==>financialDataSource,druidDataSource: " + druidDataSource);
        return druidDataSource;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "financialSqlSessionFactory")
    public SqlSessionFactory financialSqlSessionFactory(@Qualifier("financialDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 指定mapper的配置文件地址
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        // 指定xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
//    @Bean(name = "financialTransactionManager")
//    public DataSourceTransactionManager financialTransactionManager(@Qualifier("financialDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
    @Bean(name = "financialSqlSessionTemplate")
    public SqlSessionTemplate financialSqlSessionTemplate(@Qualifier("financialSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
