package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.GuestBookEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.GuestBookService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.GuestBookDAO;
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
 * @Date: 2019/3/12 23:03
 * @Description: 留言dubbo
 */

@Service("guestBookService")
public class GuestBookServiceImpl implements GuestBookService {


    private static Logger logger = LoggerFactory.getLogger(GuestBookServiceImpl.class);

    @Resource
    @Qualifier("guestBookDAO")
    private GuestBookDAO guestBookDAO;

    /**
     * @描述 留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> insertGuestBook(GuestBookEntity entity) {
        try {
            return ResultUtil.success(guestBookDAO.insertGuestBook(entity));
        } catch (Exception e) {
            logger.error("用户留言异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 审核修改
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateGuestBook(GuestBookEntity entity) {
        try {
            return ResultUtil.success(guestBookDAO.updateGuestBook(entity));
        } catch (Exception e) {
            logger.error("审核修改留言异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条留言
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.NewsEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> queryOneGuestBook(GuestBookEntity entity) {
        try {
            return ResultUtil.success(guestBookDAO.getOneGuestBook(entity));
        } catch (Exception e) {
            logger.error("查询动态异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getGuestBookPage(GuestBookEntity entity) {
        try {
            Long count = guestBookDAO.getGuestBookCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> guestBookDAO.getGuestBookPage((GuestBookEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的留言发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


}