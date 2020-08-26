package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.IotFeignClient;
import ac.cn.saya.lab.http.service.IotService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: IotServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/9 19:06
 * @Description:
 */
@Service(value = "iotService")
public class IotServiceImpl implements IotService {

    @Resource
    private IotFeignClient iotFeignClient;

    /**
     * @描述 获取网关设备类型
     * @参数 []
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.util.List < ac.cn.saya.lab.api.entity.IotGatewayTypeEntity>>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType() {
        Result<List<IotGatewayTypeEntity>> result = iotFeignClient.getIotGatewayType();
        if (!ResultUtil.checkSuccess(result)) {
            //未找到网关设备类型
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * @描述 添加网关
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotGateway(IotGatewayEntity entity, HttpServletRequest request) {
        if (null ==entity){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        // 放入用户名
        entity.setSource(userSession.getUser());
        IotIdentifyEntity authenInfo = entity.getAuthenInfo();
        if (null == authenInfo){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        if (StringUtils.isEmpty(authenInfo.getPassword())){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.addIotGateway(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改网关
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotGateway(IotGatewayEntity entity,HttpServletRequest request) {
        if (null ==entity || null == entity.getId()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        // 放入用户名
        entity.setSource(userSession.getUser());
        Result<Integer> result = iotFeignClient.editIotGateway(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除网关
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotGateway(Integer id) {
        if (null == id || id <= 0){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.deleteIotGateway(id);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 网关分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotGatewayPage(IotGatewayEntity entity) {
        // 只显示在用的网关
        entity.setRemove(1);
        return iotFeignClient.getIotGatewayPage(entity);
    }

    /**
     * @描述 获取单个网关详情
     * @参数  [id]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.IotGatewayEntity>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @Override
    public Result<IotGatewayEntity> getIotGatewayEntity(Integer id){
        if (null == id || id <= 0){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        return iotFeignClient.getIotGatewayEntity(id);
    }

    /***
     * @描述 获取网关列表-用于添加设备时的下拉选框
     * @参数  [request]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.IotGatewayEntity>>
     * @创建人  shmily
     * @创建时间  2020/8/23
     * @修改人和其它信息
     */
    @Override
    public Result<List<IotGatewayEntity>> getIotGatewayList(HttpServletRequest request){
        IotGatewayEntity entity = new IotGatewayEntity();
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        // 放入用户名
        entity.setSource(userSession.getUser());
        // 正常在用设备
        entity.setRemove(1);
        return iotFeignClient.getIotGatewayList(entity);
    }

    /**
     * @描述 添加设备
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotClient(IotClientEntity entity) {
        if (null == entity || entity.getGatewayId() == null || StringUtils.isEmpty(entity.getName())){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.addIotClient(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改设备
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotClient(IotClientEntity entity) {
        if (null == entity || entity.getId()== null){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.editIotClient(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotClient(Integer id) {
        if (null == id || id <= 0){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.deleteIotClient(id);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 设备分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotClientPage(IotClientEntity entity) {
        entity.setRemove(1);
        return iotFeignClient.getIotClientPage(entity);
    }

    /**
     * @描述 分页查看采集信息
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotCollectionPage(IotCollectionEntity entity) {
        return iotFeignClient.getIotCollectionPage(entity);
    }

    /**
     * @描述 分页查看告警报告信息
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotWarningResultPage(IotWarningResultEntity entity) {
        return iotFeignClient.getIotWarningResultPage(entity);
    }

    /**
     * @描述 添加告警规则
     * @参数 [list]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotWarningRules(List<IotWarningRulesEntity> list) {
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.addIotWarningRules(list);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改告警规则
     * @参数 [list]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotWarningRules(List<IotWarningRulesEntity> list) {
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.editIotWarningRules(list);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除告警规则
     * @参数 [list]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotWarningRules(List<Integer> list) {
        if (list.isEmpty()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.deleteIotWarningRules(list);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 分页查看终端告警规则
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotWarningRulesPage(IotWarningRulesEntity entity) {
        return iotFeignClient.getIotWarningRulesPage(entity);
    }

    /**
     * @描述 分页查看指令下发历史
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotHistoryExecutePage(IotHistoryExecuteEntity entity) {
        return iotFeignClient.getIotHistoryExecutePage(entity);
    }

    /**
     * @描述 添加终端规则预约信息
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotAppointment(IotAppointmentEntity entity) {
        if (null == entity || entity.getClientId() == null || StringUtils.isEmpty(entity.getName()) || StringUtils.isEmpty(entity.getExcuteTime()) || entity.getCommand() == null){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.addIotAppointment(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改终端规则预约信息
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotAppointment(IotAppointmentEntity entity) {
        if (null == entity || entity.getId() == null){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.editIotAppointment(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @param id
     * @描述 删除终端规则预约信息
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotAppointment(Integer id) {
        if (null == id || id <= 0){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = iotFeignClient.deleteIotAppointment(id);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 预留 记录日志
             */
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 终端规则预约信息分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/8
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getIotAppointmentPage(IotAppointmentEntity entity) {
        return iotFeignClient.getIotAppointmentPage(entity);
    }
}
