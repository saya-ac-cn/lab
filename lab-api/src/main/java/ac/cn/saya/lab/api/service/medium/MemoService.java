package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.tools.Result;

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

}
