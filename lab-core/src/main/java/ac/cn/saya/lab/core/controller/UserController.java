package ac.cn.saya.lab.core.controller;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.service.core.UserService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: UserController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-04 22:06
 * @Description: 用户相关接口(内部)
 */
@RestController
@RequestMapping("/core/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @描述 获取用户的信息
     * @参数 [user]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @GetMapping(value = "/info")
    public Result<Object> getUser(String user) {
        return userService.getUser(user);
    }

    /**
     * @描述 修改用户信息
     * @参数 [user]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @PutMapping(value = "/info")
    public Result<Object> setUser(UserEntity user) {
        return userService.setUser(user);
    }

    /**
     * @描述 查询近半年活跃情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @GetMapping(value = "/activity")
    public Result<Object> countPre6Logs(String user) {
        return userService.countPre6Logs(user);
    }

}
