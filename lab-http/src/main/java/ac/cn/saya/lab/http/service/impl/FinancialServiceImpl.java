package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.TransactionReadFeignClient;
import ac.cn.saya.lab.http.feignclient.TransactionWriteFeignClient;
import ac.cn.saya.lab.http.service.IFinancialService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: FinancialServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-04-08 22:08
 * @Description:
 */

public class FinancialServiceImpl implements IFinancialService {



    @Resource
    private RecordService recordService;

    @Resource
    private TransactionReadFeignClient transactionReadFeignClient;

    @Resource
    private TransactionWriteFeignClient transactionWriteFeignClient;

    /**
     * 获取所有的交易类别
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<TransactionTypeEntity> getTransactionType() throws Exception {
        Result<TransactionTypeEntity> result = transactionReadFeignClient.getTransactionType();
        if (!ResultUtil.checkSuccess(result)) {
            // 没有找到交易类别
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * 查看流水（这里不是明细）
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransaction(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Object> result = transactionReadFeignClient.getTransactionPage(entity);
        return result;
    }

    /**
     * 查看流水子明细
     * 根据父id，本位id，flog
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request) throws Exception {
        Result<Object> result = transactionReadFeignClient.getTransactionInfoPage(entity);
        return result;
    }

    /**
     * 查询详细的流水明细
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionFinal(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Object> result = transactionReadFeignClient.getTransactionFinalPage(entity);
        return result;
    }

    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransaction(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 修改财政记录父
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransaction(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 删除财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransaction(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 添加财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 修改财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 导出流水
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionListExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * 导出完整流水及明细
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionInfoExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * 按天统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForDay(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 按月统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForMonth(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 按年统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForYear(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForDayExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForMonthExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForYearExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
