package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.IotIdentifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: IotIdentifyDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/19 10:29
 * @Description: mqtt设备接入认证信息表
 */

@Mapper
public interface IotIdentifyDAO {

    /**
     * 添加用户认证信息
     * @param entity
     * @return
     */
    public Integer insert(IotIdentifyEntity entity);

    /**
     * 修改用户认证信息
     * @param entity
     * @return 返回影响的行数
     */
    public Integer update(IotIdentifyEntity entity);

    /**
     * 删除用户认证信息
     * @param id
     * @return 返回影响的行数
     */
    public Integer delete(@Param("id") Integer id);

    /**
     * 查询用户认证信息
     * @param entity
     * @return
     */
    public IotIdentifyEntity query(IotIdentifyEntity entity);

}
