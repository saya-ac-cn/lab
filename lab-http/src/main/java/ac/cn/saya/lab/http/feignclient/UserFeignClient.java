package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Title: UserFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 14:50
 * @Description:
 * @FeignClient同一个name,多个配置类的解决方案:在openfeign高版本2.2.1中@FeignClient里面添加了新属性ContextId
 * 参见：https://blog.csdn.net/Lou_Lan/article/details/104008492
 * 参见：https://cloud.spring.io/spring-cloud-openfeign/reference/html/#creating-feign-clients-manually
 * 描述：If we want to create multiple feign clients with the same name or url so that they would point to the same server but each with a different custom configuration then we have to use contextId attribute of the @FeignClient in order to avoid name collision of these configuration beans.
 * 如果我们要创建多个具有相同名称或URL的feign客户端，以便它们指向同一台服务器，但每个客户端具有不同的自定义配置，则必须使用的contextId属性，@FeignClient以避免这些配置Bean发生名称冲突。
 */
@FeignClient(value = "lab-core-server",contextId = "user")
public interface UserFeignClient {



    /**
     * @描述 获取用户的信息
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.UserEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/user/info")
    public Result<UserEntity> getUser(@RequestParam(value = "user") String user);

    /**
     * @描述 修改用户信息
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @PutMapping(value = "/core/user/info")
    public Result<Integer> setUser(@RequestBody UserEntity user);

    /**
     * @描述 查询近半年活跃情况
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.Object>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/user/activity")
    public Result<Map<String, Object>> countPre6Logs(@RequestParam(value = "user") String user);

}
