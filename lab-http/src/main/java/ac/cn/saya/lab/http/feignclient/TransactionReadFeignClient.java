package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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
@FeignClient(value = "lab-financial-server", contextId = "transactionRead")
public interface TransactionReadFeignClient {


    /**
     * @描述 获取所有交易类别数据
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/transactionType")
    public Result<TransactionTypeEntity> getTransactionType();

    /**
     * @描述 查看流水(根据用户、类型、日期)
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/transactionPage")
    public Result<Object> getTransactionPage(TransactionListEntity entity);

    /**
     * @描述 查看流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/transactionInfoPage")
    public Result<Object> getTransactionInfoPage(TransactionInfoEntity entity);

    /**
     * @描述 查询详细的流水明细
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/getTransactionFinalPage")
    public Result<Object> getTransactionFinalPage(TransactionListEntity entity);

    /**
     * @描述 按天分页统计财务报表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/getTransactionForDayPage")
    public Result<Object> getTransactionForDayPage(TransactionListEntity entity);

    /**
     * @描述 按月分页统计（只统计到上月的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/readgetTransactionForMonthPage")
    public Result<Object> getTransactionForMonthPage(TransactionListEntity entity);

    /**
     * @描述 按年分页统计（只统计到上一年的最后一天）
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-13
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/getTransactionForYearPage")
    public Result<Object> getTransactionForYearPage(TransactionListEntity entity);

    /**
     * @描述 查询近半年财政收支情况
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "/financial/read/pre6Financial")
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
    public Result<OutExcelEntity> outTransactionListExcel(TransactionListEntity entity);

    /**
     * @描述 导出完整流水及明细
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.OutExcelEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/21
     * @修改人和其它信息
     */
    @GetMapping(value = "outTransactionInfoExcel")
    public Result<OutExcelEntity> outTransactionInfoExcel(TransactionListEntity entity);

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/financial/read/outTransactionForDayExcel")
    public Result<OutExcelEntity> outTransactionForDayExcel(TransactionListEntity entity);

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/financial/read/outTransactionForMonthExcel")
    public Result<OutExcelEntity> outTransactionForMonthExcel(TransactionListEntity entity);

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/financial/read/outTransactionForYearExcel")
    public Result<OutExcelEntity> outTransactionForYearExcel(TransactionListEntity entity);

}
