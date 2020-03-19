package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.service.core.BackupLogService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: BackFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-11 22:08
 * @Description:数据库备份记录
 */
@FeignClient(value = "lab-core-server", contextId = "back")
public interface BackFeignClient {

    /**
     * @描述 新增备份记录
     * @参数  保存的文件路径
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @PostMapping(value = "/core/back")
    public Result<Integer> insert(@RequestParam(value = "backupUrl") String backupUrl);

    /**
     * @描述 删除备份记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/core/back")
    public Result<Integer> deleteBackup(BackupLogEntity entity);

    /**
     * @描述 查看备份详情
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/back/info")
    public Result<BackupLogEntity> getOneBackup(BackupLogEntity entity);

    /**
     * @描述 分页查看备份
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "/core/back")
    public Result<Object> getBackupPagin(BackupLogEntity entity);

}
