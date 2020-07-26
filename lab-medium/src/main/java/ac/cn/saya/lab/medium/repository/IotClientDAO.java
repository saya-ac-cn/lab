package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.IotClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Iot终端表(IotClient)表数据库访问层
 *
 * @author saya
 * @since 2020-07-19 13:24:33
 */
@Mapper
public interface IotClientDAO {

    /**
     * 查询Iot终端
     *
     * @param entity
     * @return 实例对象
     */
    public IotClientEntity query(IotClientEntity entity);

    /**
     * 分页查询Iot终端
     *
     * @param entity
     * @return 对象列表
     */
    public List<IotClientEntity> queryPage(IotClientEntity entity);


    /**
     * 查询Iot终端数量
     *
     * @param entity 实例对象
     * @return 对象列表
     */
    public Long queryCount(IotClientEntity entity);

    /**
     * 新增Iot终端
     *
     * @param iotClient 实例对象
     * @return 影响行数
     */
    public int insert(IotClientEntity iotClient);

    /**
     * 修改Iot终端
     *
     * @param iotClient 实例对象
     * @return 影响行数
     */
    public int update(IotClientEntity iotClient);

    /**
     * 删除Iot终端
     *
     * @param id 主键
     * @return 影响行数
     */
    public int deleteById(@Param("id") Integer id);

}