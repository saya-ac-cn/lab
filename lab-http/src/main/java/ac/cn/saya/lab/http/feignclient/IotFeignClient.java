package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: IotFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/8 21:32
 * @Description: iot物联网相关
 */
@FeignClient(value = "lab-medium-server", contextId = "iot",path = "medium/iot")
public interface IotFeignClient {

    /**
     * @描述 获取网关设备类型
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayTypeEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway/type")
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType();

    /**
     * @描述 添加网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "gateway")
    public Result<Integer> addIotGateway(@RequestBody IotGatewayEntity entity);

    /**
     * @描述 修改网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "gateway")
    public Result<Integer> editIotGateway(@RequestBody IotGatewayEntity entity);

    /**
     * @描述 删除网关
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "gateway")
    public Result<Integer> deleteIotGateway(@RequestParam(value = "id") Integer id);

    /**
     * @描述 网关分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway")
    public Result<Object> getIotGatewayPage(@RequestBody IotGatewayEntity entity);

    /**
     * @描述 查询网关详情
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.IotGatewayEntity>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway/{id}")
    public Result<IotGatewayEntity> getIotGatewayEntity(@PathVariable("id") Integer id);

    /**
     * @描述 获取网关下拉列表
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @GetMapping(value = "gatewayList")
    public Result<List<IotGatewayEntity>> getIotGatewayList(@RequestBody IotGatewayEntity entity);

    /**
     * @描述 添加设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "client")
    public Result<Integer> addIotClient(@RequestBody IotClientEntity entity);

    /**
     * @描述 修改设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "client")
    public Result<Integer> editIotClient(@RequestBody IotClientEntity entity);

    /**
     * @描述 删除设备
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "client/{id}")
    public Result<Integer> deleteIotClient(@PathVariable(value = "id") Integer id);

    /**
     * @描述 设备分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "client")
    public Result<Object> getIotClientPage(@RequestBody IotClientEntity entity);

    /**
     * @描述 分页查看采集信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "collection")
    public Result<Object> getIotCollectionPage(@RequestBody IotCollectionEntity entity);

    /**
     * @描述 分页查看告警报告信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "warning/result")
    public Result<Object> getIotWarningResultPage(@RequestBody IotWarningResultEntity entity);

    /**
     * @描述 添加告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "warning/rules")
    public Result<Integer> addIotWarningRules(@RequestBody List<IotWarningRulesEntity> list);

    /**
     * @描述 修改告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "warning/rules")
    public Result<Integer> editIotWarningRules(@RequestBody List<IotWarningRulesEntity> list);

    /**
     * @描述 删除告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "warning/rules")
    public Result<Integer> deleteIotWarningRules(@RequestBody List<Integer> list);

    /**
     * @描述 分页查看终端告警规则
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "warning/rules")
    public Result<Object> getIotWarningRulesPage(@RequestBody IotWarningRulesEntity entity);

    /**
     * @描述 分页查看指令下发历史
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "command/history")
    public Result<Object> getIotHistoryExecutePage(@RequestBody IotHistoryExecuteEntity entity);

    /**
     * @描述 添加终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "appointment")
    public Result<Integer> addIotAppointment(@RequestBody IotAppointmentEntity entity);

    /**
     * @描述 修改终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "appointment")
    public Result<Integer> editIotAppointment(@RequestBody IotAppointmentEntity entity);

    /**
     * @描述 删除终端规则预约信息
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "appointment")
    public Result<Integer> deleteIotAppointment(@RequestParam(value = "id") Integer id);

    /**
     * @描述 终端规则预约信息分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "appointment")
    public Result<Object> getIotAppointmentPage(@RequestBody IotAppointmentEntity entity);

}
