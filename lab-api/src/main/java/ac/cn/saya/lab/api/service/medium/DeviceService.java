package ac.cn.saya.lab.api.service.medium;

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

    // 网关分页

    // 网关数据全量  分页

    // 添加设备

    // 修改设备

    // 设备分页

}
