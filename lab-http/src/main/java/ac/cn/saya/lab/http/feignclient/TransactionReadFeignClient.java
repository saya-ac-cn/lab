package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: TransactionReadFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:57
 * @Description:财政&金融
 */
@FeignClient(value = "lab-financial-server", contextId = "transactionRead",path = "financial/read")
public interface TransactionReadFeignClient {


    /**
     * @描述 获取所有交易类别数据
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "transactionType")
    public Result<List<TransactionTypeEntity>> getTransactionType();

    /**
     * @描述 查看流水(根据用户、类型、日期)
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "transactionPage")
    public Result<Object> getTransactionPage(@RequestBody TransactionListEntity entity);

    /**
     * @描述 查看流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "transactionInfoPage")
    public Result<Object> getTransactionInfoPage(@RequestBody TransactionInfoEntity entity);

    /**
     * @描述 查询详细的流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionFinalPage")
    public Result<Object> getTransactionFinalPage(@RequestBody TransactionListEntity entity);

    /**
     * @描述 按天分页统计财务报表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForDayPage")
    public Result<Object> getTransactionForDayPage(@RequestBody TransactionListEntity entity);

    /**
     * @描述 按月分页统计（只统计到上月的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForMonthPage")
    public Result<Object> getTransactionForMonthPage(@RequestBody TransactionListEntity entity);

    /**
     * @描述 按年分页统计（只统计到上一年的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "getTransactionForYearPage")
    public Result<Object> getTransactionForYearPage(@RequestBody TransactionListEntity entity);

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "pre6Financial")
    public Result<List<TransactionListEntity>> countPre6Financial(@RequestParam(value = "user") String user);

    /**
     * @描述 导出流水
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.OutExcelEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/21
     * @修改人和其它信息
     */
    @GetMapping(value = "outTransactionListExcel")
    public Result<OutExcelEntity> outTransactionListExcel(@RequestBody TransactionListEntity entity);

    /**
     * @描述 导出完整流水及明细
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.OutExcelEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/21
     * @修改人和其它信息
     */
    @GetMapping(value = "outTransactionInfoExcel")
    public Result<OutExcelEntity> outTransactionInfoExcel(@RequestBody TransactionListEntity entity);

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForDayExcel")
    public Result<OutExcelEntity> outTransactionForDayExcel(@RequestBody TransactionListEntity entity);

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForMonthExcel")
    public Result<OutExcelEntity> outTransactionForMonthExcel(@RequestBody TransactionListEntity entity);

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "outTransactionForYearExcel")
    public Result<OutExcelEntity> outTransactionForYearExcel(@RequestBody TransactionListEntity entity);

}
