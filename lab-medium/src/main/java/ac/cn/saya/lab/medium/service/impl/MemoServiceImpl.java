package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.MemoService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.MemoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Title: MemoServiceImpl
 * @ProjectName laboratory
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020/3/12 21:13
 * @Description: 便笺对外接口
 */
@Service("memoService")
public class MemoServiceImpl implements MemoService {

    private static Logger logger = LoggerFactory.getLogger(MemoServiceImpl.class);

    @Resource
    @Qualifier("memoDAO")
    private MemoDAO memoDAO;

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> insert(MemoEntity entity) {
        try {
            // 进行加密
            entity.setContent(AesUtil.Encrypt(entity.getContent()));
            return ResultUtil.success(memoDAO.insert(entity));
        } catch (Exception e) {
            logger.error("创建便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 查询便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOne(MemoEntity entity) {
        try {
            MemoEntity result = memoDAO.query(entity);
            if (null != result && !StringUtils.isEmpty(result.getContent())) {
                result.setContent(AesUtil.Decrypt(result.getContent()));
            }
            return ResultUtil.success(result);
        } catch (Exception e) {
            logger.error("查询便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> update(MemoEntity entity) {
        try {
            // 加密处理
            entity.setContent(AesUtil.Encrypt(entity.getContent()));
            return ResultUtil.success(memoDAO.update(entity));
        } catch (Exception e) {
            logger.error("修改便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> delete(MemoEntity entity) {
        try {
            return ResultUtil.success(memoDAO.delete(entity));
        } catch (Exception e) {
            logger.error("删除便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getPage(MemoEntity entity) {
        try {
            Long count = memoDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> memoDAO.queryPage((MemoEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的便笺列表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


}
