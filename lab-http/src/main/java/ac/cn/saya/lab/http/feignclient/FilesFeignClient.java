package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: FilesFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 09:43
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "files")
public interface FilesFeignClient {

    /**
     * @描述 添加文件上传记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/files")
    public Result<Integer> insertFile(@RequestBody FilesEntity entity);

    /**
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/files")
    public Result<Integer> updateFile(@RequestBody FilesEntity entity);

    /**
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/files")
    public Result<Integer> deleteFile(FilesEntity entity);

    /**
     * @描述 查询分页后的文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/files/pagin")
    public Result<Object> getFilePage(FilesEntity entity);

    /**
     * @描述 获取一条文件信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/files/one")
    public Result<FilesEntity> getOneFile(FilesEntity entity);
}
