package ac.cn.saya.lab.api.service.core;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

/**
 * @描述 用户业务层实现类
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2020/3/13
 * @修改人和其它信息
 */
public interface UserService {

    /**
     * @描述 获取用户的信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<UserEntity> getUser(String user);

    /**
     * @描述 修改用户信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> setUser(UserEntity user);


    /**
     * @描述 查询近半年活跃情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Result<Map<String, Object>> countPre6Logs(String user);


}
