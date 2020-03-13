package ac.cn.saya.lab.financial.controller;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.service.financial.TransactionReadService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private TransactionReadService transactionReadService;

    /**
     * @描述 获取所有交易类别数据
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/transactionType")
    public Result<Object> getTransactionType(){
        return transactionReadService.selectTransactionType();
    }

    /**
     * @描述 查看流水(根据用户、类型、日期)
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "transactionPage")
    public Result<Object> getTransactionPage(TransactionListEntity entity){
        return transactionReadService.selectTransactionPage(entity);
    }

    /**
     * @描述 查看流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "transactionInfoPage")
    public Result<Object> getTransactionInfoPage(TransactionInfoEntity entity){
        return transactionReadService.selectTransactionInfoPage(entity);
    }

    /**
     * @描述 查询详细的流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionFinalPage")
    public Result<Object> getTransactionFinalPage(TransactionListEntity entity){
        return transactionReadService.selectTransactionFinalPage(entity);
    }

    /**
     * @描述 按天分页统计财务报表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForDayPage")
    public Result<Object> getTransactionForDayPage(TransactionListEntity entity){
        return transactionReadService.selectTransactionForDayPage(entity);
    }

    /**
     * @描述 按月分页统计（只统计到上月的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForMonthPage")
    public Result<Object> getTransactionForMonthPage(TransactionListEntity entity){
        return transactionReadService.selectTransactionForMonthPage(entity);
    }

    /**
     * @描述 按年分页统计（只统计到上一年的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForYearPage")
    public Result<Object> getTransactionForYearPage(TransactionListEntity entity){
        return transactionReadService.selectTransactionForYearPage(entity);
    }
}
