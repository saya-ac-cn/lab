package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.NewsEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/news")
    public Result<Object> publishNews(@RequestBody NewsEntity entity);

    /**
     * @描述 编辑修改动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/news")
    public Result<Object> editNews(@RequestBody NewsEntity entity);

    /**
     * @描述 删除动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/news")
    public Result<Object> deleteNews(NewsEntity entity);

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/one")
    public Result<Object> getOneNews(NewsEntity entity);

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
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/news/nearby")
    public Result<Object> getNewsPreAndNext(@RequestParam(value = "newsId") Integer newsId);

}
