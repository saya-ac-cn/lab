package ac.cn.saya.lab.api.service.financial;


import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: IFinancialService
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/4 17:03
 * @Description: 财政数据库提供的相关服务
 */

public interface IFinancialService {

    /**
     * 查询近半年财政收支情况
     * @param user
     * @return
     */
    public Result<List<TransactionListEntity>> countPre6Financial(String user);

    /**
     * 获取所有的交易类别
     *
     * @return
     * @throws Exception
     */
    public Result<List<TransactionTypeEntity>> getTransactionType();

    /**
     * 查看流水（这里不是明细）
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> getTransaction(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 查看流水子明细
     * 根据父id，本位id，flog
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> getTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request);

    /**
     * 查询详细的流水明细总数
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> getTransactionFinal(TransactionListEntity entity, HttpServletRequest request);


    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> insertTransaction(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 修改财政记录父
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> updateTransaction(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 删除财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> deleteTransaction(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 添加财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> insertTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request);

    /**
     * 修改财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> updateTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request);

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> deleteTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request);

    /**
     * 导出流水
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Result<OutExcelEntity> outTransactionListExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response);


    /**
     * 导出完整流水及明细
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Result<OutExcelEntity> outTransactionInfoExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response);

    /**
     * 按天统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> totalTransactionForDay(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 按月统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> totalTransactionForMonth(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 按年统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> totalTransactionForYear(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<OutExcelEntity> outTransactionForDayExcel(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<OutExcelEntity> outTransactionForMonthExcel(TransactionListEntity entity, HttpServletRequest request);

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Result<OutExcelEntity> outTransactionForYearExcel(TransactionListEntity entity, HttpServletRequest request);

}
