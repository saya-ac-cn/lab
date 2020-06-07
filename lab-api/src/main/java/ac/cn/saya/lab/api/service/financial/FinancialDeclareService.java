package ac.cn.saya.lab.api.service.financial;


import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.List;

/**
 * @Title: FinancialDeclareService
 * @ProjectName laboratory
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-04-22 21:47
 * @Description: 财政申报中间件
 */
public interface FinancialDeclareService {

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public List<TransactionListEntity> countPre6Financial(String user);

    /**
     * 获取所有交易类别数据
     *
     * @return
     */
    public List<TransactionTypeEntity> selectTransactionType();

    /**
     * 查看流水
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    public List<TransactionListEntity> selectTransactionPage(TransactionListEntity entity);

    /**
     * 查看流水总数
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    public Long selectTransactionCount(TransactionListEntity entity);

    /**
     * 查看流水明细
     *
     * @param entity
     * @return
     */
    public List<TransactionInfoEntity> selectTransactionInfoPage(TransactionInfoEntity entity);

    /**
     * 查看流水明细总数
     *
     * @param entity
     * @return
     */
    public Long selectTransactionInfoCount(TransactionInfoEntity entity);

    /**
     * 查询详细的流水明细
     *
     * @param entity
     * @return
     */
    public List<TransactionInfoEntity> selectTransactionFinalPage(TransactionListEntity entity);

    /**
     * 查询详细的流水明细总数
     *
     * @param entity
     * @return
     */
    public Long selectTransactionFinalCount(TransactionListEntity entity);

    /**
     * 按天分页统计财务报表
     *
     * @param entity
     * @return
     */
    public List<TransactionListEntity> selectTransactionForDayPage(TransactionListEntity entity);

    /**
     * 按天统计财务报表流水总数
     *
     * @param entity
     * @return
     */
    public Long selectTransactionForDayCount(TransactionListEntity entity);

    /**
     * 按月分页统计（只统计到上月的最后一天）
     *
     * @param entity
     * @return
     */
    public List<TransactionListEntity> selectTransactionForMonthPage(TransactionListEntity entity);

    /**
     * 按月统计（只统计到上月的最后一天）总数
     *
     * @param entity
     * @return
     */
    public Long selectTransactionForMonthCount(TransactionListEntity entity);

    /**
     * 按年分页统计（只统计到上一年的最后一天）
     *
     * @param entity
     * @return
     */
    public List<TransactionListEntity> selectTransactionForYearPage(TransactionListEntity entity);

    /**
     * 按年统计（只统计到上一年的最后一天）总数
     *
     * @param entity
     * @return
     */
    public Long selectTransactionForYearCount(TransactionListEntity entity);

    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @return
     */
    public Result<Object> insertTransaction(TransactionListEntity entity);

    /**
     * 修改财政记录父
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public Result<Object> updateTransaction(TransactionListEntity entity);

    /**
     * 这里是级联删除
     * 删除财政记录父+子
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    public Result<Object> deleteTransaction(TransactionListEntity entity, String user);

    /**
     * 单行
     * 添加财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    public Result<Object> insertTransactioninfo(TransactionInfoEntity entity, String user);

    /**
     * 单行
     * 修改财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    public Result<Object> updateTransactioninfo(TransactionInfoEntity entity, String user);

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    public Result<Object> deleteTransactioninfo(TransactionInfoEntity entity, String user);

}
