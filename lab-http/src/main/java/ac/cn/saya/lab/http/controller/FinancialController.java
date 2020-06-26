package ac.cn.saya.lab.http.controller;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.http.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: FinancialController
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/4 17:51
 * @Description: 财政数据接口
 */

@RestController
@RequestMapping(value = "/backend/api/financial")
public class FinancialController {

    @Autowired
    private IFinancialService financialService;

    /**
     * 获取财政流水（这里不是明细）
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "transaction")
    public Result<Object> getTransaction(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.getTransaction(entity, request);
    }

    /**
     * 获取所有的支付类别
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "transactionType")
    public Result<List<TransactionTypeEntity>> getTransactionType() {
        return financialService.getTransactionType();
    }

    /**
     * 获取支付子明细
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "transactionInfo")
    public Result<Object> getTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request) {
        return financialService.getTransactionInfo(entity, request);
    }

    /**
     * 获取完整的流水明细
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "transactionFinal")
    public Result<Object> getTransactionFinal(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.getTransactionFinal(entity, request);
    }

    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "insertTransaction")
    public Result<Object> insertTransaction(@RequestBody TransactionListEntity entity, HttpServletRequest request) {
        return financialService.insertTransaction(entity, request);
    }

    /**
     * 修改财政记录父
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping(value = "updateTransaction")
    public Result<Object> updateTransaction(@RequestBody TransactionListEntity entity, HttpServletRequest request) {
        return financialService.updateTransaction(entity, request);
    }

    /**
     * 删除财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "deleteTransaction")
    public Result<Object> deleteTransaction(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.deleteTransaction(entity, request);
    }

    /**
     * 添加财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "insertTransactioninfo")
    public Result<Object> insertTransactioninfo(@RequestBody TransactionInfoEntity entity, HttpServletRequest request) {
        return financialService.insertTransactioninfo(entity, request);
    }

    /**
     * 修改财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping(value = "updateTransactioninfo")
    public Result<Object> updateTransactioninfo(@RequestBody TransactionInfoEntity entity, HttpServletRequest request) {
        return financialService.updateTransactioninfo(entity, request);
    }

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "deleteTransactioninfo")
    public Result<Object> deleteTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) {
        return financialService.deleteTransactioninfo(entity, request);
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
    @GetMapping(value = "outTransactionListExcel")
    public Result<Object> outTransactionListExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        return financialService.outTransactionListExcel(entity, request, response);
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
    @GetMapping(value = "outTransactionInfoExcel")
    public Result<Object> outTransactionInfoExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        return financialService.outTransactionInfoExcel(entity, request, response);
    }

    /**
     * 按天统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "totalTransactionForDay")
    public Result<Object> totalTransactionForDay(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.totalTransactionForDay(entity, request);
    }

    /**
     * 按月统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "totalTransactionForMonth")
    public Result<Object> totalTransactionForMonth(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.totalTransactionForMonth(entity, request);
    }

    /**
     * 按年统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "totalTransactionForYear")
    public Result<Object> totalTransactionForYear(TransactionListEntity entity, HttpServletRequest request) {
        return financialService.totalTransactionForYear(entity, request);
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
    @GetMapping(value = "outTransactionForDayExcel")
    public Result<Object> outTransactionForDayExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        return financialService.outTransactionForDayExcel(entity, request, response);
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
    @GetMapping(value = "outTransactionForMonthExcel")
    public Result<Object> outTransactionForMonthExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        return financialService.outTransactionForMonthExcel(entity, request, response);
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
    @GetMapping(value = "outTransactionForYearExcel")
    public Result<Object> outTransactionForYearExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        return financialService.outTransactionForYearExcel(entity, request, response);
    }


}
