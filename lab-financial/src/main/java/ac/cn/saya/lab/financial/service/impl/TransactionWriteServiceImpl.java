package ac.cn.saya.lab.financial.service.impl;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.financial.TransactionWriteService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.financial.repository.TransactionWriteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: TransactionWriteServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-03-11 22:49
 * @Description:财政业务相关-对外可修改业务层接口
 */

@Service
public class TransactionWriteServiceImpl implements TransactionWriteService {

    private static Logger logger = LoggerFactory.getLogger(TransactionWriteServiceImpl.class);


    @Resource
    @Qualifier("transactionWriteDAO")
    private TransactionWriteDAO transactionWriteDAO;

    /**
     * @描述 写入到财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertTransactionInfo(TransactionInfoEntity entity) {
        try {
            return ResultUtil.success(transactionWriteDAO.insertTransactionInfo(entity));
        } catch (Exception e) {
            logger.error("写入到财政明细表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 写入到财政父表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertTransactionList(TransactionListEntity entity) {
        Integer tradeId = null;
        try {
            tradeId = transactionWriteDAO.insertTransactionList(entity);
            if (tradeId > 0) {
                // 取出主键回填的值
                tradeId = entity.getTradeId();
            }
            return ResultUtil.success(tradeId);
        } catch (Exception e) {
            logger.error("写入到财政父表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> updateTransactionInfo(TransactionInfoEntity entity) {
        try {
            return ResultUtil.success(transactionWriteDAO.updateTransactionInfo(entity));
        } catch (Exception e) {
            logger.error("修改财政明细表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改财政父表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> updateTransactionList(TransactionListEntity entity) {
        try {
            return ResultUtil.success(transactionWriteDAO.updateTransactionList(entity));
        } catch (Exception e) {
            logger.error("修改财政父表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteTransactionInfo(Integer id, String source) {
        try {
            return ResultUtil.success(transactionWriteDAO.deleteTransactionInfo(id));
        } catch (Exception e) {
            logger.error("删除财政明细表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除财政父表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteTransactionList(Integer tradeId, String source) {
        try {
            return ResultUtil.success(transactionWriteDAO.deleteTransactionList(tradeId, source));
        } catch (Exception e) {
            logger.error("删除财政父表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
