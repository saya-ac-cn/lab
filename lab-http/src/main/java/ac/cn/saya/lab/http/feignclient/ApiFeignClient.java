package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.ApiEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: ApiFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 13:46
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "api",path = "medium/api")
public interface ApiFeignClient {


    /**
     * @描述 添加接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insertApi(@RequestBody ApiEntity entity);

    /**
     * @描述 编辑接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> editApi(@RequestBody ApiEntity entity);

    /**
     * @描述 删除接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteApi(@RequestBody ApiEntity entity);

    /**
     * @描述 查询一条接口信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "query")
    public Result<ApiEntity> getOneApi(@RequestBody ApiEntity entity);

    /**
     * @描述 获取分页后的接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getApiPage(@RequestBody ApiEntity entity);
}
