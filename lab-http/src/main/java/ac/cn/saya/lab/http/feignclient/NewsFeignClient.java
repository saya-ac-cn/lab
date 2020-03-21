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
@FeignClient(value = "lab-medium-server", contextId = "news")
public interface NewsFeignClient {


    /**
     * @描述 发布动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/news")
    public Result<Integer> publishNews(@RequestBody NewsEntity entity);

    /**
     * @描述 编辑修改动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/news")
    public Result<Integer> editNews(@RequestBody NewsEntity entity);

    /**
     * @描述 删除动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/news")
    public Result<Integer> deleteNews(NewsEntity entity);

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.NewsEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/one")
    public Result<NewsEntity> getOneNews(NewsEntity entity);

    /**
     * @描述 获取分页后的动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/pagin")
    public Result<Object> getNewsPage(NewsEntity entity);

    /**
     * @描述 获取上一条和下一条动态
     * @参数 [newsId]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String   ,   java.lang.String>>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/nearby")
    public Result<Map<String,String>> getNewsPreAndNext(@RequestParam(value = "newsId") Integer newsId);

    /**
     * @描述 查询近半年发表的动态
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/pre6MonthNews")
    public Result<Map<String,String>> countPre6MonthNews(@RequestParam(value = "user") String user);

}
