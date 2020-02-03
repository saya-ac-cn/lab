package ac.cn.saya.lab.api.service.financial;


import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;

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
     * @返回值 java.lang.Integer 返回写入状态标志位
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer insertTransactionInfo(TransactionInfoEntity entity);

    /**
     * @描述 写入到财政父表
     * @参数 [entity]
     * @返回值 java.lang.Integer 返回主键回填的值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer insertTransactionList(TransactionListEntity entity);

    /**
     * @描述 修改财政明细表
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer updateTransactionInfo(TransactionInfoEntity entity);

    /**
     * @描述 修改财政父表
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer updateTransactionList(TransactionListEntity entity);

    /**
     * @描述 删除财政明细表
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer deleteTransactionInfo(Integer id, String source);

    /**
     * @描述 删除财政父表
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/12/27
     * @修改人和其它信息
     */
    public Integer deleteTransactionList(Integer tradeId, String source);

}
