package ac.cn.saya.lab.medium.repository;


import ac.cn.saya.lab.api.entity.ApiEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: ApiDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author saya.ac.cn-刘能凯
 * @Date: 2020-02-29
 * @Description:
 * 对外接口管理DAO
 */

@Mapper
public interface ApiDAO {

    /**
     * @描述 创建接口
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer insertApi(ApiEntity entity);

    /**
     * @描述 修改接口
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer updateApi(ApiEntity entity);

    /**
     * @描述 删除接口
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer deleteApi(ApiEntity entity);

    /**
     * @描述 查询一条接口详情
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public ApiEntity getOneApi(ApiEntity entity);

    /**
     * @描述 获取指定条件接口
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public List<ApiEntity> getApiPage(ApiEntity entity);

    /**
     * @描述 获取指定条件接口总数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Long getApiCount(ApiEntity entity);


}
