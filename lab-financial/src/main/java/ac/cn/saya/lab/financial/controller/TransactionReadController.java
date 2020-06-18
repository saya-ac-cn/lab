package ac.cn.saya.lab.financial.controller;

import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.service.financial.IFinancialService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: TransactionReadController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:57
 * @Description:财政&金融内部只读接口
 */
@RestController
@RequestMapping(value = "/financial/read")
public class TransactionReadController {

    @Autowired
    private IFinancialService financialService;

    /**
     * @Title获取所有交易类别数据
     * @Params  []
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.TransactionTypeEntity>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-06-13
     * @Description
     */
    @GetMapping(value = "/transactionType")
    public Result<List<TransactionTypeEntity>> getTransactionType(){
        return financialService.getTransactionType();
    }

    /**
     * @Title 查看流水(根据用户、类型、日期)
     * @Params
     * @Return
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-06-13
     * @Description
     */
    @GetMapping(value = "/transactionPage")
    public Result<Object> getTransactionPage(TransactionListEntity entity,HttpServletRequest request){
        return financialService.getTransaction(entity,request);
    }

    /**
     * @Title 查看流水明细
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.TransactionInfoEntity>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-06-13
     * @Description
     */
    @GetMapping(value = "/transactionInfoPage")
    public Result<Object> getTransactionInfoPage(TransactionInfoEntity entity, HttpServletRequest request){
        return financialService.getTransactionInfo(entity,request);
    }

    /**
     * @描述 查询详细的流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/getTransactionFinalPage")
    public Result<Object> getTransactionFinalPage(TransactionListEntity entity,HttpServletRequest request){
        return financialService.getTransactionFinal(entity, request);
    }

    /**
     * @Title 按天分页统计财务报表
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.TransactionListEntity>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-06-13
     * @Description
     */
    @GetMapping(value = "/getTransactionForDayPage")
    public Result<Object> getTransactionForDayPage(TransactionListEntity entity,HttpServletRequest request){
        return financialService.totalTransactionForDay(entity, request);
    }

    /**
     * @描述 按月分页统计（只统计到上月的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/getTransactionForMonthPage")
    public Result<Object> getTransactionForMonthPage(TransactionListEntity entity,HttpServletRequest request){
        return financialService.totalTransactionForMonth(entity,request);
    }

    /**
     * @描述 按年分页统计（只统计到上一年的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/getTransactionForYearPage")
    public Result<Object> getTransactionForYearPage(TransactionListEntity entity,HttpServletRequest request){
        return financialService.totalTransactionForYear(entity,request);
    }

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForDayExcel")
    public Result<OutExcelEntity> outTransactionForDayExcel(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return financialService.outTransactionForDayExcel(entity, request);
    }

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForMonthExcel")
    public Result<OutExcelEntity> outTransactionForMonthExcel(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return financialService.outTransactionForMonthExcel(entity, request);
    }

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForYearExcel")
    public Result<OutExcelEntity> outTransactionForYearExcel(TransactionListEntity entity, HttpServletRequest request) throws Exception {
        return financialService.outTransactionForYearExcel(entity, request);
    }

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "/pre6Financial")
    public Result<List<TransactionListEntity>> countPre6Financial(@RequestParam(value = "user") String user){
        return financialService.countPre6Financial(user);
    }
}
