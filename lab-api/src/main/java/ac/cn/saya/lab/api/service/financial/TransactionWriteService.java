package ac.cn.saya.lab.api.service.financial;


import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.tools.Result;

/**
 * @Title: TransactionWriteService
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/12/27 22:49
 * @Description:财政业务相关-对外可修改业务层接口
 */

public interface TransactionWriteService {


    /**
     * @描述 写入到财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> insertTransactionInfo(TransactionInfoEntity entity);

    /**
     * @描述 写入到财政父表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> insertTransactionList(TransactionListEntity entity);

    /**
     * @描述 修改财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> updateTransactionInfo(TransactionInfoEntity entity);

    /**
     * @描述 修改财政父表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> updateTransactionList(TransactionListEntity entity);

    /**
     * @描述 删除财政明细表
     * @参数 [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> deleteTransactionInfo(Integer id, String source);

    /**
     * @描述 删除财政父表
     * @参数  [tradeId, source]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    public Result<Integer> deleteTransactionList(Integer tradeId, String source);

}
