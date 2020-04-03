package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

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
    public Result<Integer> insertFile(FilesEntity entity);

    /**
     * @param entity
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> updateFile(FilesEntity entity);

    /**
     * @param entity
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> deleteFile(FilesEntity entity);

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
    public Result<FilesEntity> getOneFile(FilesEntity entity);

    /**
     * @描述 查询近半年文件上传情况
     * @参数 [user]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    public Result<Map<String,String>> countPre6Files(String user);

    /**
     * @Title   统计文件总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-03
     * @Description
     */
    public Result<Long> totalFileCount(FilesEntity entity);

}
