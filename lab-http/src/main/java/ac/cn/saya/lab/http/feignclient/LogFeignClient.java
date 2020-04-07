package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.entity.LogTypeEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Title: LogFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 14:50
 * @Description:
 */
@FeignClient(value = "lab-core-server", contextId = "log",path = "/core/log")
public interface LogFeignClient {


    /**
     * @描述 记录日志
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @PostMapping(value = "/record")
    public Result<Integer> insert(@RequestBody LogEntity entity);

    /**
     * @描述 获取所有的日志类别
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogTypeEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/type")
    public Result<LogTypeEntity> selectLogType();

    /**
     * @描述 分页查询日志 按用户、类别、日期
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/display")
    public Result<Object> display(LogEntity entity);

    /**
     * @描述 查询用户最近的操作
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/recently")
    public Result<LogEntity> queryRecentlyLog(@RequestParam(value = "user") String user);

    /**
     * @Title 获取日志总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "/count")
    public Result<Long> getCount(LogEntity entity);

    /**
     * @Title 获取日志列表(分页) 需配合quertCount使用
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogEntity>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "/list")
    public Result<List<LogEntity>> getList(LogEntity entity);

}
