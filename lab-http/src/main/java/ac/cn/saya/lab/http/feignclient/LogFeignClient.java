package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Title: LogFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 14:50
 * @Description:
 */
@FeignClient(value = "lab-core-server", contextId = "log")
public interface LogFeignClient {


    /**
     * @描述 记录日志
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @PostMapping(value = "/core/log/record")
    public Result<Object> insert(@RequestBody LogEntity entity);

    /**
     * @描述 获取所有的日志类别
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/log/type")
    public Result<Object> selectLogType();

    /**
     * @描述 分页查询日志 按用户、类别、日期
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-04
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/log/display")
    public Result<Object> display(LogEntity entity);

}
