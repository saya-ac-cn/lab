package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.IotWarningRulesEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Iot告警规则表(IotWarningRules)表数据库访问层
 *
 * @author saya
 * @since 2020-07-26 09:26:34
 */
@Mapper
public interface IotWarningRulesDAO {

    /**
     * 查询Iot告警规则
     *
     * @param entity
     * @return 实例对象
     */
    public IotWarningRulesEntity query(IotWarningRulesEntity entity);

    /**
     * 分页查询Iot告警规则
     *
     * @param entity
     * @return 对象列表
     */
    public List<IotWarningRulesEntity> queryPage(IotWarningRulesEntity entity);


    /**
     * 查询Iot告警规则数量
     *
     * @param entity 实例对象
     * @return 对象列表
     */
    public Long queryCount(IotWarningRulesEntity entity);

    /**
     * 新增Iot告警规则
     *
     * @param list 实例对象
     * @return 影响行数
     */
    public int insert(List<IotWarningRulesEntity> list);

    /**
     * 修改告警规则
     *
     * @param list 实例对象
     * @return 影响行数
     */
    public int update(List<IotWarningRulesEntity> list);

    /**
     * 删除告警规则
     *
     * @param list 主键
     * @return 影响行数
     */
    public int deleteById(List<Integer> list);

}