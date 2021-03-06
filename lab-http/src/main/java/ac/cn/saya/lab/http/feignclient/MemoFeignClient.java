package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: MemoFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:01
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "memo",path = "medium/memo")
public interface MemoFeignClient {

    /**
     * @描述 创建便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insert(@RequestBody MemoEntity entity);

    /**
     * @描述 查询便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.MemoEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<MemoEntity> getOne(@RequestBody MemoEntity entity);

    /**
     * @描述 修改便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> update(@RequestBody MemoEntity entity);

    /**
     * @描述 删除便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> delete(@RequestBody MemoEntity entity);

    /**
     * @描述 获取分页后的便笺
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getPage(@RequestBody MemoEntity entity);

    /**
     * @Title 统计便笺总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    @GetMapping(value = "totalCount")
    public Result<Long> totalCount(@RequestBody MemoEntity entity);

    /**
     * @Title 查询近半年便笺情况
     * @Params  [user]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-08
     * @Description
     */
    @GetMapping(value = "pre6Memo")
    public Result<Map<String,String>> countPre6Memo(@RequestParam(value = "user") String user);

}
