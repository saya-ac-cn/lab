package ac.cn.saya.lab.financial.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * @Title: JDBCBaseConnection
 * @ProjectName laboratory
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-03-03 16:03
 * @Description: 基类Dao
 * 自动实现相关的Mybatis变量
 * 把配置文件中的been注入到此
 */

public class JDBCBaseConnection {

    @Resource
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSession getSqlSession(){
        //打开SqlSession会话
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

}
