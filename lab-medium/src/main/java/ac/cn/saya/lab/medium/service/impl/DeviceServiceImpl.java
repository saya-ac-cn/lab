package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.DeviceService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.meta.Metadata;
import ac.cn.saya.lab.medium.repository.IotClientDAO;
import ac.cn.saya.lab.medium.repository.IotGatewayDAO;
import ac.cn.saya.lab.medium.repository.IotGatewayTypeDAO;
import ac.cn.saya.lab.medium.repository.IotIdentifyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: DeviceServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author shmily
 * @Date: 2020/7/29 21:00
 * @Description:
 */
@Service("deviceService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor= MyException.class)
public class DeviceServiceImpl implements DeviceService {

    private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Resource
    private IotGatewayTypeDAO iotGatewayTypeDAO;

    @Resource
    private IotIdentifyDAO iotIdentifyDAO;

    @Resource
    private IotGatewayDAO iotGatewayDAO;

    @Resource
    private IotClientDAO iotClientDAO;

    @Resource
    private Metadata metadata;

    /**
     * @描述 获取Iot设备类型
     * @参数
     * @返回值 IotGatewayTypeEntity 集合
     * @创建人 shmily
     * @创建时间 2020/7/29
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<List<IotGatewayTypeEntity>> getIotGatewayType() {
        try {
            List<IotGatewayTypeEntity> result = iotGatewayTypeDAO.queryAll();
            if (result.isEmpty()){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("获取Iot设备类型异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 添加网关
     * @参数
     * @返回值
     * @创建人 shmily
     * @创建时间 2020/7/29
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotGateway(IotGatewayEntity entity) {
        if (null ==entity){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        IotIdentifyEntity authenInfo = entity.getAuthenInfo();
        if (null == authenInfo){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        if (StringUtils.isEmpty(authenInfo.getUsername()) || StringUtils.isEmpty(authenInfo.getPassword())){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        // 本例采用固定写法
        authenInfo.setSalt("sha256");
        // 加密密码
        authenInfo.setPassword(Sha256Utils.getSHA256(authenInfo.getPassword()));
        if (StringUtils.isEmpty(entity.getCode()) || StringUtils.isEmpty(entity.getName()) || StringUtils.isEmpty(entity.getSource()) || null == entity.getDeviceType()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        try {
            if (iotIdentifyDAO.insert(authenInfo)>0){
                entity.setAuthenId(authenInfo.getId());
                if (iotGatewayDAO.insert(entity)>0){
                    return ResultUtil.success();
                }
            }
            return ResultUtil.error(ResultEnum.DB_ERROR);
        } catch (Exception e) {
            logger.error("添加网关设备异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改网关
     * @参数
     * @返回值
     * @创建人 shmily
     * @创建时间 2020/7/29
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotGateway(IotGatewayEntity entity) {
        if (null ==entity || null == entity.getId()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        IotIdentifyEntity authenInfo = entity.getAuthenInfo();
        if (null != authenInfo || null != entity.getAuthenId()){
            return ResultUtil.error(ResultEnum.NOT_PARAMETER);
        }
        try {
            iotGatewayDAO.update(entity);
            iotIdentifyDAO.update(authenInfo);
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("修改网关设备异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除网关信息
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotGateway(Integer id) {
        IotGatewayEntity gatewayEntity = new IotGatewayEntity();
        gatewayEntity.setId(id);
        try {
            IotGatewayEntity query = iotGatewayDAO.query(gatewayEntity);
            if (null == query){
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            // 置位删除
            query.setRemove(2);
            IotClientEntity clientEntity = new IotClientEntity();
            clientEntity.setGatewayId(id);
            clientEntity.setRemove(2);
            // 先通过网关id删除设备信息
            if (iotClientDAO.updateByGatewayId(clientEntity) < 0){
                return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"删除设备信息异常");
            }
            // 查询出设备信息，并从元数据中删除
            List<IotClientEntity> clientEntities = iotClientDAO.queryByGatewayId(id);
            metadata.removeClients(clientEntities);
            // 删除认证信息
            if (iotIdentifyDAO.delete(query.getAuthenId()) < 0){
                return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"删除认证信息异常");
            }
            // 删除网关信息
            if (iotGatewayDAO.update(query) < 0){
                return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"删除网关信息异常");
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除网关信息异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 网关分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getIotGatewayPage(IotGatewayEntity entity) {
        try {
            Long count = iotGatewayDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> iotGatewayDAO.queryPage((IotGatewayEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询分页后的网关列表发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 添加设备
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotClient(IotClientEntity entity) {
        try {
            if (iotClientDAO.insert(entity) >= 0){
                metadata.doRefreshClient(entity);
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"添加设备异常");
        } catch (Exception e) {
            logger.error("添加设备发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改设备
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotClient(IotClientEntity entity) {
        try {
            if (iotClientDAO.update(entity) >= 0){
                metadata.doRefreshClient(entity);
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"修改设备异常");
        } catch (Exception e) {
            logger.error("修改设备发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除设备
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotClient(Integer id) {
        try {
            IotClientEntity clientEntity = iotClientDAO.query(new IotClientEntity(id));
            if (null == clientEntity){
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            if (iotClientDAO.deleteById(id) >= 0){
                metadata.removeClient(clientEntity);
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"删除设备异常");
        } catch (Exception e) {
            logger.error("删除设备发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 设备分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getIotClientPage(IotClientEntity entity) {
        try {
            Long count = iotClientDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> iotClientDAO.queryPage((IotClientEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询分页后的设备分页发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
