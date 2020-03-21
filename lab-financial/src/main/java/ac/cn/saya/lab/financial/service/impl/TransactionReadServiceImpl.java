package ac.cn.saya.lab.financial.service.impl;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.financial.TransactionReadService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.financial.repository.ProceDureDAO;
import ac.cn.saya.lab.financial.repository.TransactionReadDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: TransactionReadServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 22:49
 * @Description:财政业务相关-对外只读业务层接口
 */
@Service
public class TransactionReadServiceImpl implements TransactionReadService {

    private static Logger logger = LoggerFactory.getLogger(TransactionReadServiceImpl.class);

    @Resource
    @Qualifier("transactionReadDAO")
    private TransactionReadDAO transactionReadDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;

    /**
     * 获取所有交易类别数据
     *
     * @return
     */
    @Override
    public Result<TransactionTypeEntity> selectTransactionType() {
        try {
            List<TransactionTypeEntity> list = transactionReadDAO.selectTransactionType();
            if (list.size() <= 0) {
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("查询交易类别时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionPage(TransactionListEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionPage((TransactionListEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查看流水时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * 查看流水明细
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionInfoPage(TransactionInfoEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionInfoCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionInfoPage((TransactionInfoEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查看流水明细发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * 查询详细的流水明细
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionFinalPage(TransactionListEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionFinalCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionFinalPage((TransactionListEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询详细的流水明细发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按天分页统计财务报表
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionForDayPage(TransactionListEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionForDayCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionForDayPage((TransactionListEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("按天分页统计财务报表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按月分页统计（只统计到上月的最后一天）
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionForMonthPage(TransactionListEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionForMonthCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionForMonthPage((TransactionListEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("按月分页统计（只统计到上月的最后一天）异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按年分页统计（只统计到上一年的最后一天）
     *
     * @param entity
     * @return
     */
    @Override
    public Result<Object> selectTransactionForYearPage(TransactionListEntity entity) {
        try {
            Long count = transactionReadDAO.selectTransactionForYearCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> transactionReadDAO.selectTransactionForYearPage((TransactionListEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("按年分页统计（只统计到上一年的最后一天）异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    @Override
    public Result<Object> countPre6Financial(String user) {
        try {
            List<TransactionListEntity> list = proceDureDAO.countPre6Financial(user);
            if (list.isEmpty()){
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("查询近半年财政收支情况失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
