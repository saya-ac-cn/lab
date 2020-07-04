package ac.cn.saya.lab.medium.service.impl;


import ac.cn.saya.lab.api.entity.NewsEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.NewsService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.ProceDureDAO;
import ac.cn.saya.lab.medium.repository.NewsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Title: NewsServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2019/1/10 23:03
 * @Description:
 */

@Service("newsService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor=MyException.class)
public class NewsServiceImpl implements NewsService {


    private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Resource
    @Qualifier("newsDAO")
    private NewsDAO newsDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;

    /**
     * @描述 发布动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> publishNews(NewsEntity entity) {
        try {
            Integer result = newsDAO.insertNews(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("发布动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 编辑修改动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editNews(NewsEntity entity) {
        try {
            Integer result = newsDAO.updateNews(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("编辑修改动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteNews(NewsEntity entity) {
        try {
            Integer result = newsDAO.deleteNews(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NewsEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<NewsEntity> getOneNews(NewsEntity entity) {
        try {
            NewsEntity result = newsDAO.getOneNews(entity);
            if (null != result){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getNewsPage(NewsEntity entity) {
        try {
            Long count = newsDAO.getNewsCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> newsDAO.getNewsPage((NewsEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的动态发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 获取上一条和下一条动态
     * @参数 [newsId]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Map<String,String>> getNewsPreAndNext(Integer newsId) {
        try {
            Map<String, String> map = proceDureDAO.getNewsNotesPreAndNext(1, newsId);
            if (!map.isEmpty()){
                return ResultUtil.success(map);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("获取上一条和下一条动态时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询近半年发表的动态
     * @参数 [user]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Map<String,String>> countPre6MonthNews(String user) {
        try {
            Map<String, Object> map = proceDureDAO.countPre6MonthNews(user);
            if (!map.isEmpty()){
                return ResultUtil.success(map);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询近半年发表的动态时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 统计动态总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Long> totalNewsCount(NewsEntity entity){
        try {
            Long count = newsDAO.getNewsCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("统计动态总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
