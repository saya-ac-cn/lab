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

import javax.annotation.Resource;

/**
 * @Title: NewsServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2019/1/10 23:03
 * @Description:
 */

@Service("newsService")
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
    public Result<Object> publishNews(NewsEntity entity) {
        try {
            return ResultUtil.success(newsDAO.insertNews(entity));
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
    public Result<Object> editNews(NewsEntity entity) {
        try {
            return ResultUtil.success(newsDAO.updateNews(entity));
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
    public Result<Object> deleteNews(NewsEntity entity) {
        try {
            return ResultUtil.success(newsDAO.deleteNews(entity));
        } catch (Exception e) {
            logger.error("删除动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条动态
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.NewsEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOneNews(NewsEntity entity) {
        try {
            return ResultUtil.success(newsDAO.getOneNews(entity));
        } catch (Exception e) {
            logger.error("查询动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @param entity
     * @描述 获取分页后的动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
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
     * @创建时间 2019-03-12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNewsPreAndNext(Integer newsId) {
        try {
            return ResultUtil.success(proceDureDAO.getNewsNotesPreAndNext(1, newsId));
        } catch (Exception e) {
            logger.error("获取上一条和下一条动态时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
