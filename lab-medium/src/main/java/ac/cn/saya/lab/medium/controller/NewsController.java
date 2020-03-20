package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.NewsEntity;
import ac.cn.saya.lab.api.service.medium.NewsService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: NewsController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:14
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * @描述 发布动态
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> publishNews(@RequestBody NewsEntity entity){
        return newsService.publishNews(entity);
    }

    /**
     * @描述 编辑修改动态
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> editNews(@RequestBody NewsEntity entity){
        return newsService.editNews(entity);
    }

    /**
     * @描述 删除动态
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteNews(NewsEntity entity){
        return newsService.deleteNews(entity);
    }

    /**
     * @描述 查询一条动态
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.NewsEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<NewsEntity> getOneNews(NewsEntity entity){
        return newsService.getOneNews(entity);
    }

    /**
     * @描述 获取分页后的动态
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getNewsPage(NewsEntity entity){
        return newsService.getNewsPage(entity);
    }

    /**
     * @描述 获取上一条和下一条动态
     * @参数  [newsId]
     * @返回值  java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/nearby")
    public Result<Map<String,String>> getNewsPreAndNext(@RequestParam(value = "newsId") Integer newsId){
        return newsService.getNewsPreAndNext(newsId);
    }

}
