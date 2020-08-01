package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.IotCollectionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Iot终端表(IotCollection)表数据库访问层
 *
 * @author saya
 * @since 2020-07-23 13:24:33
 */
@Repository
public interface IotCollectionDAO {

    /**
     * 查询Iot采集信息
     *
     * @param entity
     * @return 实例对象
     */
    public IotCollectionEntity query(IotCollectionEntity entity);

    /**
     * 分页查询Iot采集信息
     *
     * @param entity
     * @return 对象列表
     */
    public List<IotCollectionEntity> queryPage(IotCollectionEntity entity);


    /**
     * 查询Iot采集信息数量
     *
     * @param entity 实例对象
     * @return 对象列表
     */
    public Long queryCount(IotCollectionEntity entity);

    /**
     * 新增Iot采集信息
     *
     * @param iotClient 实例对象
     * @return 影响行数
     */
    public int insert(IotCollectionEntity iotClient);
}