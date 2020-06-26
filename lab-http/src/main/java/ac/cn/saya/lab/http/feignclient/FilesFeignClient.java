package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: FilesFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 09:43
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "files",path = "medium/files")
public interface FilesFeignClient {

    /**
     * @描述 添加文件上传记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insertFile(@RequestBody FilesEntity entity);

    /**
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> updateFile(@RequestBody FilesEntity entity);

    /**
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteFile(@RequestBody FilesEntity entity);

    /**
     * @描述 查询分页后的文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getFilePage(@RequestBody FilesEntity entity);

    /**
     * @描述 获取一条文件信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<FilesEntity> getOneFile(@RequestBody FilesEntity entity);

    /**
     * @描述 查询近半年文件上传情况
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-21
     * @修改人和其它信息
     */
    @GetMapping(value = "pre6Files")
    public Result<Map<String,String>> countPre6Files(@RequestParam(value = "user") String user);

    /**
     * @Title 统计文件总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-03
     * @Description
     */
    @GetMapping(value = "totalFileCount")
    public Result<Long> totalFileCount(@RequestBody FilesEntity entity);

}
