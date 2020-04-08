package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

/**
 * @Title: MemoService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-12 21:15
 * @Description:
 */

public interface MemoService {

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> insert(MemoEntity entity);

    /**
     * @描述 查询便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<MemoEntity> getOne(MemoEntity entity);

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> update(MemoEntity entity);

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> delete(MemoEntity entity);

    /**
     * @描述 获取分页后的便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> getPage(MemoEntity entity);

    /**
     * @Title 统计便笺总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    public Result<Long> totalCount(MemoEntity entity);

    /**
     * @Title 查询近半年留言情况
     * @Params  [user]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-08
     * @Description
     */
    public Result<Map<String,String>> countPre6Memo(String user);

}
