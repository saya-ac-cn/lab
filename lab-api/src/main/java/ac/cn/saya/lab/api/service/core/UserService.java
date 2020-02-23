package ac.cn.saya.lab.api.service.core;

import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * @描述 用户业务层实现类
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2018/11/11
 * @修改人和其它信息
 */
public interface UserService {

    /**
     * @描述 获取用户的信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    public UserEntity getUser(String user);

    /**
     * @描述 修改用户信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    public Integer setUser(UserEntity user);

    /**
     * @描述 查询近半年的动态发布情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6MonthNews(String user);

    /**
     * @描述 查询近半年活跃情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Logs(String user);

    /**
     * @描述 查询近半年文件上传情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Files(String user);

    /**
     * @描述 查询近半年留言情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Map<String, Object> countPre6Board();

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public List<TransactionListEntity> countPre6Financial(String user);

    /**
     * @描述 查询用户当日的计划安排，最近的一次操作
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-19
     * @修改人和其它信息
     */
    public Map<String, Object> queryUserRecentlyInfo(String user);

}
