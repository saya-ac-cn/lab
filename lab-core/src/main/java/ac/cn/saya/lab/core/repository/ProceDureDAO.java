package ac.cn.saya.lab.core.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: ProceDureDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-04 21:56
 * @Description:
 * 数据库原生MySQL操作
 */
@Repository("proceDureDAO")
public class ProceDureDAO extends JDBCBaseConnection{

    /**
     * @描述 调用存储过程查询近半年活跃情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Logs(String user) {
        Map<String, Object> result = null;
        SqlSession sqlSession = null;
        //连接对象
        Connection sqlCon = null;
        try {
            //获取sqlSession
            sqlSession = getSqlSession();
            //建立jdbc连接
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            CallableStatement cs = sqlCon.prepareCall("{Call countPre6Logs(?)}");
            //设置参数
            cs.setString(1, user);
            //执行
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            result = new LinkedHashMap();
            while (rs.next()) {
                result.put(rs.getString("totalCount"), rs.getLong("count"));
            }
            cs.close();
            sqlCon.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //及时关闭资源
            if (sqlSession != null) {
                sqlSession.close();
            }
            return result;
        }
    }

}
