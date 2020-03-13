package ac.cn.saya.lab.api.service.financial;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.List;

/**
 * @Title: TransactionReadService
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 22:49
 * @Description:财政业务相关-对外只读业务层接口
 */
public interface TransactionReadService {


    /**
     * 获取所有交易类别数据
     *
     * @return
     */
    public Result<Object> selectTransactionType();

    /**
     * 查看流水
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionPage(TransactionListEntity entity);


    /**
     * 查看流水明细
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionInfoPage(TransactionInfoEntity entity);


    /**
     * 查询详细的流水明细
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionFinalPage(TransactionListEntity entity);


    /**
     * 按天分页统计财务报表
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionForDayPage(TransactionListEntity entity);


    /**
     * 按月分页统计（只统计到上月的最后一天）
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionForMonthPage(TransactionListEntity entity);


    /**
     * 按年分页统计（只统计到上一年的最后一天）
     *
     * @param entity
     * @return
     */
    public Result<Object> selectTransactionForYearPage(TransactionListEntity entity);


}
