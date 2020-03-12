package ac.cn.saya.lab.core.controller;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.service.core.BackupLogService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: BackController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-11 22:08
 * @Description:数据库备份记录
 */
@RestController
@RequestMapping("/core/back")
public class BackController {

    @Autowired
    private BackupLogService backupLogService;

    /**
     * @描述 新增备份记录
     * @参数  保存的文件路径
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insert(@RequestParam(value = "backupUrl") String backupUrl){
        return backupLogService.insertBackup(backupUrl);
    }

    /**
     * @描述 删除备份记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Object> deleteBackup(BackupLogEntity entity){
        return backupLogService.deleteBackup(entity);
    }

    /**
     * @描述 查看备份详情
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "/info")
    public Result<Object> getOneBackup(BackupLogEntity entity){
        return backupLogService.getOneBackup(entity);
    }

    /**
     * @描述 分页查看备份
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "/")
    public Result<Object> getBackupPagin(BackupLogEntity entity){
        return backupLogService.getBackupPagin(entity);
    }

}
