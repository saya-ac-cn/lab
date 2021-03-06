package ac.cn.saya.lab.medium.repository;

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
 * @Description: 数据库原生MySQL操作
 */
@Repository("proceDureDAO")
public class ProceDureDAO extends JDBCBaseConnection {

    /**
     * @描述 调用存储过程查询近半年发表的动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6MonthNews(String user) {
        Map<String, Object> result = null;
        SqlSession sqlSession = null;
        //连接对象
        Connection sqlCon = null;
        try {
            //获取sqlSession
            sqlSession = getSqlSession();
            //建立jdbc连接
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            CallableStatement cs = sqlCon.prepareCall("{Call countPre6News(?)}");
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

    /**
     * @描述 调用存储过程查询近半年文件上传情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Files(String user) {
        Map<String, Object> result = null;
        SqlSession sqlSession = null;
        //连接对象
        Connection sqlCon = null;
        try {
            //获取sqlSession
            sqlSession = getSqlSession();
            //建立jdbc连接
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            CallableStatement cs = sqlCon.prepareCall("{Call countPre6Files(?)}");
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

    /**
     * @描述 调用存储过程查询近半年便笺情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Memo(String user) {
        Map<String, Object> result = null;
        SqlSession sqlSession = null;
        //连接对象
        Connection sqlCon = null;
        try {
            //获取sqlSession
            sqlSession = getSqlSession();
            //建立jdbc连接
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            CallableStatement cs = sqlCon.prepareCall("{Call countPre6Memo(?)}");
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

    /**
     * @描述 获取上一条和下一条动态或笔记的id
     * @参数 type 调用的存储过程类别 1 为动态 2 为笔记
     * @参数 id 传入的动态或笔记编号
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, String> getNewsNotesPreAndNext(Integer type, Integer Id) {
        Map<String, String> result = null;
        SqlSession sqlSession = null;
        //连接对象
        Connection sqlCon = null;
        try {
            //获取sqlSession
            sqlSession = getSqlSession();
            //建立jdbc连接
            sqlCon = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            CallableStatement cs = null;
            if (type == 1) {
                cs = sqlCon.prepareCall("{Call newsPreAndNext(?)}");
            } else {
                cs = sqlCon.prepareCall("{Call notesPreAndNext(?)}");
            }
            //设置参数
            cs.setInt(1, Id);
            //执行
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            result = new LinkedHashMap();
            while (rs.next()) {
                String[] data = (rs.getString("id")).split(":");
                result.put(data[1], data[0]);
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
