package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.tools.Result;

/**
 * @Title: FilesService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-12 21:03
 * @Description:
 */

public interface FilesService {

    /***
     * @描述 添加文件上传记录
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     * @param entity
     */
    public Result<Object> insertFile(FilesEntity entity);

    /**
     * @param entity
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> updateFile(FilesEntity entity);

    /**
     * @param entity
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> deleteFile(FilesEntity entity);

    /**
     * @param entity
     * @描述 查询分页后的文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> getFilePage(FilesEntity entity);

    /**
     * @param entity
     * @描述 获取一条文件信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> getOneFile(FilesEntity entity);

}
