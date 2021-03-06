package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.NewsEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

/**
 * @Title: NewsService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-12 21:22
 * @Description:
 */

public interface NewsService {

    /**
     * @描述 发布动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> publishNews(NewsEntity entity);

    /**
     * @描述 编辑修改动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> editNews(NewsEntity entity);

    /**
     * @描述 删除动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> deleteNews(NewsEntity entity);

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NewsEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<NewsEntity> getOneNews(NewsEntity entity);

    /**
     * @描述 获取分页后的动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> getNewsPage(NewsEntity entity);

    /**
     * @描述 获取上一条和下一条动态
     * @参数 [newsId]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    public Result<Map<String,String>> getNewsPreAndNext(Integer newsId);

    /**
     * @描述 查询近半年发表的动态
     * @参数 [user]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    public Result<Map<String,String>> countPre6MonthNews(String user);

    /**
     * @Title 统计动态总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    public Result<Long> totalNewsCount(NewsEntity entity);

}
