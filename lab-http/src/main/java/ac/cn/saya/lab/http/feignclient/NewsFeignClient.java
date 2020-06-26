package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.NewsEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: NewsFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:14
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "news",path = "medium/news")
public interface NewsFeignClient {


    /**
     * @描述 发布动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> publishNews(@RequestBody NewsEntity entity);

    /**
     * @描述 编辑修改动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> editNews(@RequestBody NewsEntity entity);

    /**
     * @描述 删除动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteNews(@RequestBody NewsEntity entity);

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.NewsEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<NewsEntity> getOneNews(@RequestBody NewsEntity entity);

    /**
     * @描述 获取分页后的动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getNewsPage(@RequestBody NewsEntity entity);

    /**
     * @描述 获取上一条和下一条动态
     * @参数 [newsId]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String   ,   java.lang.String>>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "nearby")
    public Result<Map<String,String>> getNewsPreAndNext(@RequestParam(value = "newsId") Integer newsId);

    /**
     * @描述 查询近半年发表的动态
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "pre6MonthNews")
    public Result<Map<String,String>> countPre6MonthNews(@RequestParam(value = "user") String user);

    /**
     * @Title 统计动态总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    @GetMapping(value = "totalNewsCount")
    public Result<Long> totalNewsCount(@RequestBody NewsEntity entity);

}
