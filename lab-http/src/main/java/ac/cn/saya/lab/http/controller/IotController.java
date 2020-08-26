package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.service.IotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: IotController
 * @ProjectName lab
 * @Description: TODO
 * @Author shmily
 * @Date: 2020/8/9 19:39
 * @Description:
 */
@RestController
@RequestMapping(value = "/backend/api/iot")
public class IotController {

    @Autowired
    @Qualifier("iotService")
    private IotService iotService;

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
        return iotService.getIotGatewayType();
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
    public Result<Integer> addIotGateway(@RequestBody IotGatewayEntity entity, HttpServletRequest request){
        return iotService.addIotGateway(entity,request);
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
    public Result<Integer> editIotGateway(@RequestBody IotGatewayEntity entity,HttpServletRequest request){
        return iotService.editIotGateway(entity,request);
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
        return iotService.deleteIotGateway(id);
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
    public Result<Object> getIotGatewayPage(IotGatewayEntity entity,IotIdentifyEntity authenInfo){
        if (null != authenInfo && authenInfo.getEnable() != null && null != entity){
            entity.setAuthenInfo(authenInfo);
        }
        return iotService.getIotGatewayPage(entity);
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
        return iotService.getIotGatewayEntity(id);
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
    public Result<List<IotGatewayEntity>> getIotGatewayList(HttpServletRequest request){
        return iotService.getIotGatewayList(request);
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
        return iotService.addIotClient(entity);
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
        return iotService.editIotClient(entity);
    }

    /**
     * @描述 删除设备
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  shmily
     * @创建时间  2020/8/8
     * @修改人和其它信息
     */
    @DeleteMapping(value = "client")
    public Result<Integer> deleteIotClient(@RequestParam(value = "id") Integer id){
        return iotService.deleteIotClient(id);
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
    public Result<Object> getIotClientPage(IotClientEntity entity){
        return iotService.getIotClientPage(entity);
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
    public Result<Object> getIotCollectionPage(IotCollectionEntity entity){
        return iotService.getIotCollectionPage(entity);
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
    public Result<Object> getIotWarningResultPage(IotWarningResultEntity entity){
        return iotService.getIotWarningResultPage(entity);
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
        return iotService.addIotWarningRules(list);
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
        return iotService.editIotWarningRules(list);
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
    public Result<Integer> deleteIotWarningRules(List<Integer> list){
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        return iotService.deleteIotWarningRules(list);
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
    public Result<Object> getIotWarningRulesPage(IotWarningRulesEntity entity){
        return iotService.getIotWarningRulesPage(entity);
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
    public Result<Object> getIotHistoryExecutePage(IotHistoryExecuteEntity entity){
        return iotService.getIotHistoryExecutePage(entity);
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
        return iotService.addIotAppointment(entity);
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
        return iotService.editIotAppointment(entity);
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
    public Result<Integer> deleteIotAppointment(@RequestParam(value = "id")  Integer id){
        return iotService.deleteIotAppointment(id);
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
    public Result<Object> getIotAppointmentPage(IotAppointmentEntity entity){
        return iotService.getIotAppointmentPage(entity);
    }

}
