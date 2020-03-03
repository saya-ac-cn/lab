package ac.cn.saya.lab.api.service.core;

import ac.cn.saya.lab.api.entity.BackupLogEntity;

import java.util.List;

/**
 * @Title: BackupLogService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-03 22:46
 * @Description:系统备份日志记录
 */

public interface BackupLogService {

    /**
     * @描述 新增备份记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    public Integer insertBackup(String backupUrl);

    /**
     * @描述 删除备份数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    public Integer deleteBackup(BackupLogEntity entity);

    /**
     * @描述 查询单条备份记录
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.ApiEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    public BackupLogEntity getOneBackup(BackupLogEntity entity);

    /**
     * @描述 分页查看备份记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    public List<BackupLogEntity> getBackupPagin(BackupLogEntity entity);

    /**
     * @描述 查看备份记录总数
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    public Long getBackupCount(BackupLogEntity entity);

}
