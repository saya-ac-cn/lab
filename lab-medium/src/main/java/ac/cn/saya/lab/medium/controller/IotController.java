package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.service.medium.CollectionService;
import ac.cn.saya.lab.api.service.medium.CommandService;
import ac.cn.saya.lab.api.service.medium.DeviceService;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Title: IotController
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/8 20:59
 * @Description: 物联网相关控制器
 */

@RestController
@RequestMapping(value = "medium/iot")
public class IotController {

    @Qualifier("deviceService")
    @Autowired
    private DeviceService deviceService;

    @Qualifier("commandService")
    @Autowired
    private CommandService commandService;

    @Qualifier("collectionService")
    @Autowired
    private CollectionService collectionService;

    /**
     * @描述 获取网关设备类型
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayTypeEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway/type")
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType(){
        return deviceService.getIotGatewayType();
    }

    /**
     * @描述 添加网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "gateway")
    public Result<Integer> addIotGateway(@RequestBody IotGatewayEntity entity){
        return deviceService.addIotGateway(entity);
    }

    /**
     * @描述 修改网关
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "gateway")
    public Result<Integer> editIotGateway(@RequestBody IotGatewayEntity entity){
        return deviceService.editIotGateway(entity);
    }

    /**
     * @描述 删除网关
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "gateway")
    public Result<Integer> deleteIotGateway(@RequestParam(value = "id") Integer id){
        return deviceService.deleteIotGateway(id);
    }

    /**
     * @描述 网关分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway")
    public Result<Object> getIotGatewayPage(@RequestBody IotGatewayEntity entity){
        return deviceService.getIotGatewayPage(entity);
    }

    /**
     * @描述 查询网关详情
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.IotGatewayEntity>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @GetMapping(value = "gateway/{id}")
    public Result<IotGatewayEntity> getIotGatewayEntity(@PathVariable("id") Integer id){
        return deviceService.getIotGatewayEntity(id);
    }

    /**
     * @描述 获取网关下拉列表
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @GetMapping(value = "gatewayList")
    public Result<List<IotGatewayEntity>> getIotGatewayList(@RequestBody IotGatewayEntity entity){
        return deviceService.getIotGatewayList(entity);
    }

    /**
     * @描述 添加设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "client")
    public Result<Integer> addIotClient(@RequestBody IotClientEntity entity){
        return deviceService.addIotClient(entity);
    }

    /**
     * @描述 修改设备
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "client")
    public Result<Integer> editIotClient(@RequestBody IotClientEntity entity){
        return deviceService.editIotClient(entity);
    }

    /**
     * @描述 删除设备
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "client/{id}")
    public Result<Integer> deleteIotClient(@PathVariable(value = "id") Integer id){
        return deviceService.deleteIotClient(id);
    }

    /**
     * @描述 设备分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "client")
    public Result<Object> getIotClientPage(@RequestBody IotClientEntity entity){
        return deviceService.getIotClientPage(entity);
    }

    /**
     * @描述 分页查看采集信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "collection")
    public Result<Object> getIotCollectionPage(@RequestBody IotCollectionEntity entity){
        return collectionService.getIotCollectionPage(entity);
    }

    /**
     * @描述 分页查看告警报告信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "warning/result")
    public Result<Object> getIotWarningResultPage(@RequestBody IotWarningResultEntity entity){
        return collectionService.getIotWarningResultPage(entity);
    }

    /**
     * @描述 添加告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "warning/rules")
    public Result<Integer> addIotWarningRules(@RequestBody List<IotWarningRulesEntity> list){
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        return collectionService.addIotWarningRules(list);
    }

    /**
     * @描述 修改告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "warning/rules")
    public Result<Integer> editIotWarningRules(@RequestBody List<IotWarningRulesEntity> list){
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        return collectionService.editIotWarningRules(list);
    }

    /**
     * @描述 删除告警规则
     * @参数  [list]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "warning/rules")
    public Result<Integer> deleteIotWarningRules(@RequestBody List<Integer> list){
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        return collectionService.deleteIotWarningRules(list);
    }

    /**
     * @描述 分页查看终端告警规则
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "warning/rules")
    public Result<Object> getIotWarningRulesPage(@RequestBody IotWarningRulesEntity entity){
        return collectionService.getIotWarningRulesPage(entity);
    }

    /**
     * @描述 分页查看指令下发历史
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "command/history")
    public Result<Object> getIotHistoryExecutePage(@RequestBody IotHistoryExecuteEntity entity){
        return commandService.getIotHistoryExecutePage(entity);
    }

    /**
     * @描述 添加终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PostMapping(value = "appointment")
    public Result<Integer> addIotAppointment(@RequestBody IotAppointmentEntity entity){
        return commandService.addIotAppointment(entity);
    }

    /**
     * @描述 修改终端规则预约信息
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @PutMapping(value = "appointment")
    public Result<Integer> editIotAppointment(@RequestBody IotAppointmentEntity entity){
        return commandService.editIotAppointment(entity);
    }

    /**
     * @描述 删除终端规则预约信息
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "appointment")
    public Result<Integer> deleteIotAppointment(@RequestParam(value = "id") Integer id){
        return commandService.deleteIotAppointment(id);
    }

    /**
     * @描述 终端规则预约信息分页
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @GetMapping(value = "appointment")
    public Result<Object> getIotAppointmentPage(@RequestBody IotAppointmentEntity entity){
        return commandService.getIotAppointmentPage(entity);
    }

}
