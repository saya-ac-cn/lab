package ac.cn.saya.lab.core.service.impl;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.core.BackupLogService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.core.repository.BackupLogDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: NotesService
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 18:52
 * @Description: 平台数据库备份日志记录
 */
@Service
public class BackupLogServiceImpl implements BackupLogService {

    private static Logger logger = LoggerFactory.getLogger(BackupLogServiceImpl.class);

    @Resource
    @Qualifier("backupLogDAO")
    private BackupLogDAO backupLogDAO;

    /**
     * @描述 新增备份记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertBackup(String backupUrl) {
        try {
            Integer backup = backupLogDAO.insertBackup(backupUrl);
            if (backup >= 0){
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR);
        } catch (Exception e) {
            logger.error("新增备份记录异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 删除备份数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteBackup(BackupLogEntity entity) {
        try {
            Integer backup = backupLogDAO.deleteBackup(entity);
            if (backup >= 0){
                return ResultUtil.success();
            }
            return ResultUtil.error(ResultEnum.DB_ERROR);
        } catch (Exception e) {
            logger.error("删除备份数据异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询单条备份记录
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.ApiEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<BackupLogEntity> getOneBackup(BackupLogEntity entity) {
        try {
            BackupLogEntity backupOne = backupLogDAO.getBackupOne(entity);
            if (null != backupOne){
                return ResultUtil.success(backupOne);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询单条备份记录异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 分页查看备份记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getBackupPagin(BackupLogEntity entity) {
        try {
            Long count = backupLogDAO.getBackupCount(entity);
            if (count > 0){
                Result<Object> result = PageTools.page(count, entity, (condition) -> backupLogDAO.getBackupPagin((BackupLogEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("分页查看备份记录发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述  查看备份数据列表
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.BackupLogEntity>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/25
     * @修改人和其它信息
     */
    @Override
    public Result<List<BackupLogEntity>> getBackupList(BackupLogEntity entity) {
        try {
            List<BackupLogEntity> list = backupLogDAO.getBackupList(entity);
            if (list.size() > 0) {
                return ResultUtil.success(list);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查看备份记录列表发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查看备份数据总数
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/25
     * @修改人和其它信息
     */
    @Override
    public Result<Long> getBackupCount(BackupLogEntity entity) {
        try {
            Long count = backupLogDAO.getBackupCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查看备份记录总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
