package ac.cn.saya.lab.financial.controller;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.service.financial.IFinancialService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: TransactionWriteController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 21:09
 * @Description:
 */
@RestController
@RequestMapping(value = "financial/write")
public class TransactionWriteController {

    @Autowired
    private IFinancialService financialService;

    /**
     * @描述 写入到财政明细表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @PostMapping(value = "transactionInfo/{user}")
    public Result<Object> insertTransactionInfo(@RequestBody TransactionInfoEntity entity, @PathVariable(value = "user") String user){
        return financialService.insertTransactionInfo(entity,user);
    }

    /**
     * @描述 写入到财政父表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @PostMapping(value = "transactionList")
    public Result<Object> insertTransactionList(@RequestBody TransactionListEntity entity){
        return financialService.insertTransaction(entity);
    }

    /**
     * @描述 修改财政明细表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @PutMapping(value = "transactionInfo/{user}")
    public Result<Object> updateTransactionInfo(@RequestBody TransactionInfoEntity entity, @PathVariable(value = "user") String user){
        return financialService.updateTransactionInfo(entity,user);
    }

    /**
     * @描述 修改财政父表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @PutMapping(value = "transactionList")
    public Result<Object> updateTransactionList(@RequestBody TransactionListEntity entity){
        return financialService.updateTransaction(entity);
    }

    /**
     * @描述 删除财政明细表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @DeleteMapping(value = "transactionInfo/{user}")
    public Result<Object> deleteTransactionInfo(@RequestBody TransactionInfoEntity entity, @PathVariable(value = "user") String user){
        return financialService.deleteTransactionInfo(entity,user);
    }

    /**
     * @描述 删除财政父表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @DeleteMapping(value = "transactionList")
    public Result<Object> deleteTransactionList(@RequestBody TransactionListEntity entity){
        return financialService.deleteTransaction(entity);
    }
}
