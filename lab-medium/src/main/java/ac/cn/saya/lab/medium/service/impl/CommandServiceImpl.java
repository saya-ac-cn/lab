package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.IotAppointmentEntity;
import ac.cn.saya.lab.api.entity.IotHistoryExecuteEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.CommandService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.IotAppointmentDAO;
import ac.cn.saya.lab.medium.repository.IotHistoryExecuteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Title: CommandServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/1 17:12
 * @Description: 指令相关业务集
 */
@Service("commandService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor= MyException.class)
public class CommandServiceImpl implements CommandService {

    private static Logger logger = LoggerFactory.getLogger(CommandServiceImpl.class);

    @Resource
    private IotHistoryExecuteDAO iotHistoryExecuteDAO;

    @Resource
    private IotAppointmentDAO iotAppointmentDAO;

    /**
     * @描述 分页查看指令下发历史
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getIotHistoryExecutePage(IotHistoryExecuteEntity entity) {
        try {
            Long count = iotHistoryExecuteDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> iotHistoryExecuteDAO.queryPage((IotHistoryExecuteEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("分页查看指令下发历史列表发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 添加终端规则预约信息
     * @参数
     * @返回值
     * @创建人 shmily
     * @创建时间 2020/7/29
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> addIotAppointment(IotAppointmentEntity entity) {
        try {
            if (iotAppointmentDAO.insert(entity) >= 0){
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"添加终端规则预约异常");
        } catch (Exception e) {
            logger.error("添加终端规则预约发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改终端规则预约信息
     * @参数
     * @返回值
     * @创建人 shmily
     * @创建时间 2020/7/29
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editIotAppointment(IotAppointmentEntity entity) {
        try {
            // 这里已经在数据库层面做了限制，已经下发的指令不允许再次修改
            if (iotAppointmentDAO.update(entity) >= 0){
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"修改终端规则预约异常");
        } catch (Exception e) {
            logger.error("修改终端规则预约发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除终端规则预约信息
     * @参数 [id]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteIotAppointment(Integer id) {
        try {
            if (iotAppointmentDAO.deleteById(id) >= 0){
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR.getCode(),"删除终端规则预约异常");
        } catch (Exception e) {
            logger.error("删除终端规则预约发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 终端规则预约信息分页
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 shmily
     * @创建时间 2020/8/1
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getIotAppointmentPage(IotAppointmentEntity entity) {
        try {
            Long count = iotAppointmentDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> iotAppointmentDAO.queryPage((IotAppointmentEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询分页后的终端规则预约信息发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
