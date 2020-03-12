package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.MemoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: MemoDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author saya.ac.cn-刘能凯
 * @Date: 2020-02-29
 * @Description:
 * 便笺DAO层
 */

@Mapper
public interface MemoDAO {

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public int insert(MemoEntity entity);

    /**
     * @描述 查询一条便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public MemoEntity query(MemoEntity entity);

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public int update(MemoEntity entity);

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public int delete(MemoEntity entity);

    /**
     * @描述 获取分页后的便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public List<MemoEntity> queryPage(MemoEntity entity);

    /**
     * @描述 获取便笺总数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-20
     * @修改人和其它信息
     */
    public Long queryCount(MemoEntity entity);

}
