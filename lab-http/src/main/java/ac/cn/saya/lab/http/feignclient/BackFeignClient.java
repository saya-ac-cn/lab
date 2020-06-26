package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: BackFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-11 22:08
 * @Description:数据库备份记录
 */
@FeignClient(value = "lab-core-server", contextId = "back",path = "core/back")
public interface BackFeignClient {

    /**
     * @描述 新增备份记录
     * @参数  保存的文件路径
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insert(@RequestParam(value = "backupUrl") String backupUrl);

    /**
     * @描述 删除备份记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteBackup(@RequestBody BackupLogEntity entity);

    /**
     * @描述 查看备份详情
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "info")
    public Result<BackupLogEntity> getOneBackup(@RequestBody BackupLogEntity entity);

    /**
     * @描述 分页查看备份
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-11
     * @修改人和其它信息
     */
    @GetMapping(value = "/")
    public Result<Object> getBackupPagin(@RequestBody BackupLogEntity entity);

    /**
     * @描述  查看备份数据列表
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.BackupLogEntity>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/25
     * @修改人和其它信息
     */
    @GetMapping(value = "list")
    public Result<List<BackupLogEntity>> getBackupList(@RequestBody BackupLogEntity entity);

    /**
     * @描述 查看备份数据总数
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/25
     * @修改人和其它信息
     */
    @GetMapping(value = "count")
    public Result<Long> getBackupCount(@RequestBody BackupLogEntity entity);

}
