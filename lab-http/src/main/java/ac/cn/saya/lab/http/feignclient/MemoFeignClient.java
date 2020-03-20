package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: MemoFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:01
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "memo")
public interface MemoFeignClient {

    /**
     * @描述 创建便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/memo")
    public Result<Integer> insert(@RequestBody MemoEntity entity);

    /**
     * @描述 查询便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.MemoEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/memo/one")
    public Result<MemoEntity> getOne(MemoEntity entity);

    /**
     * @描述 修改便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/memo")
    public Result<Integer> update(@RequestBody MemoEntity entity);

    /**
     * @描述 删除便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/memo")
    public Result<Integer> delete(MemoEntity entity);

    /**
     * @描述 获取分页后的便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/memo/pagin")
    public Result<Object> getPage(MemoEntity entity);

}
