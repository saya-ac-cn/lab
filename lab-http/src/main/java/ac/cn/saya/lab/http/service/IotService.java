package ac.cn.saya.lab.http.service;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: IotService
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/9 19:01
 * @Description:
 */

public interface IotService {

    /**
     * @描述 获取网关设备类型
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayTypeEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType();

    /**
     * @描述 添加网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> addIotGateway(IotGatewayEntity entity, HttpServletRequest request);

    /**
     * @描述 修改网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> editIotGateway(IotGatewayEntity entity,HttpServletRequest request);

    /**
     * @描述 删除网关
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotGateway(Integer id);

    /**
     * @描述 网关分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotGatewayPage(IotGatewayEntity entity);

    /**
     * @描述 获取单个网关详情
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.IotGatewayEntity>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    public Result<IotGatewayEntity> getIotGatewayEntity(Integer id);

    /***
     * @描述 获取网关列表-用于添加设备时的下拉选框
     * @参数  [request]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    public Result<List<IotGatewayEntity>> getIotGatewayList(HttpServletRequest request);

    /**
     * @描述 添加设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> addIotClient(IotClientEntity entity);

    /**
     * @描述 修改设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> editIotClient(IotClientEntity entity);

    /**
     * @描述 删除设备
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotClient(Integer id);

    /**
     * @描述 设备分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotClientPage(IotClientEntity entity);

    /**
     * @描述 分页查看采集信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotCollectionPage(IotCollectionEntity entity);

    /**
     * @描述 分页查看告警报告信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotWarningResultPage(IotWarningResultEntity entity);

    /**
     * @描述 添加告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> addIotWarningRules(List<IotWarningRulesEntity> list);

    /**
     * @描述 修改告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> editIotWarningRules(List<IotWarningRulesEntity> list);

    /**
     * @描述 删除告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotWarningRules(List<Integer> list);

    /**
     * @描述 分页查看终端告警规则
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotWarningRulesPage(IotWarningRulesEntity entity);

    /**
     * @描述 分页查看指令下发历史
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotHistoryExecutePage(IotHistoryExecuteEntity entity);

    /**
     * @描述 添加终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> addIotAppointment(IotAppointmentEntity entity);

    /**
     * @描述 修改终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> editIotAppointment(IotAppointmentEntity entity);

    /**
     * @描述 删除终端规则预约信息
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Integer> deleteIotAppointment(Integer id);

    /**
     * @描述 终端规则预约信息分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    public Result<Object> getIotAppointmentPage(IotAppointmentEntity entity);

}
