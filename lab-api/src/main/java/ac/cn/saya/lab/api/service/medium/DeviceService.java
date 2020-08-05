package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.IotClientEntity;
import ac.cn.saya.lab.api.entity.IotGatewayEntity;
import ac.cn.saya.lab.api.entity.IotGatewayTypeEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.List;

/**
 * @Title: DeviceService
 * @ProjectName lab
 * @Description: TODO
 * @Author shmily
 * @Date: 2020/7/29 20:43
 * @Description: 设备相关操作接口
 */

public interface DeviceService {

    /**
     * @描述 获取Iot设备类型
     * @参数
     * @返回值 IotGatewayTypeEntity 集合
     * @创建人  shmily
     * @创建时间  2020/7/29
     * @修改人和其它信息
     */
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType();

    /**
     * @描述 添加网关
     * @参数
     * @返回值
     * @创建人  shmily
     * @创建时间  2020/7/29
     * @修改人和其它信息
     */
    public Result<Integer> addIotGateway(IotGatewayEntity entity);

    /**
     * @描述 修改网关
     * @参数
     * @返回值
     * @创建人  shmily
     * @创建时间  2020/7/29
     * @修改人和其它信息
     */
    public Result<Integer> editIotGateway(IotGatewayEntity entity);

    /**
     * @描述 删除网关信息
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotGateway(Integer id);

    /**
     * @描述 网关分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Object> getIotGatewayPage(IotGatewayEntity entity);

    /**
     * @描述 添加设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Integer> addIotClient(IotClientEntity entity);

    /**
     * @描述 修改设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Integer> editIotClient(IotClientEntity entity);

    /**
     * @描述 删除设备
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotClient(Integer id);

    /**
     * @描述 设备分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/1
     * @修改人和其它信息
     */
    public Result<Object> getIotClientPage(IotClientEntity entity);

}
