package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.service.medium.FilesService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: FilesController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 09:43
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

    /**
     * @描述 添加文件上传记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insertFile(@RequestBody FilesEntity entity){
        return filesService.insertFile(entity);
    }

    /**
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Object> updateFile(@RequestBody FilesEntity entity){
        return filesService.updateFile(entity);
    }

    /**
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Object> deleteFile(FilesEntity entity){
        return filesService.deleteFile(entity);
    }

    /**
     * @描述 查询分页后的文件列表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getFilePage(FilesEntity entity){
        return filesService.getFilePage(entity);
    }

    /**
     * @描述 获取一条文件信息
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<Object> getOneFile(FilesEntity entity){
        return filesService.getFilePage(entity);
    }
}
