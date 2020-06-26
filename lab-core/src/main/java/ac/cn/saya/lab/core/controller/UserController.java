package ac.cn.saya.lab.core.controller;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.service.core.UserService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: UserController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-04 22:06
 * @Description: 用户相关接口(内部)
 */
@RestController
@RequestMapping(value = "core/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @描述 获取用户的信息
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.UserEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "info")
    public Result<UserEntity> getUser(@RequestParam(value = "user") String user) {
        return userService.getUser(user);
    }

    /**
     * @描述 修改用户信息
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @PutMapping(value = "info")
    public Result<Integer> setUser(@RequestBody UserEntity user) {
        return userService.setUser(user);
    }

    /**
     * @描述 查询近半年活跃情况
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.Object>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "activity")
    public Result<Map<String, Object>> countPre6Logs(@RequestParam(value = "user") String user) {
        return userService.countPre6Logs(user);
    }

}
