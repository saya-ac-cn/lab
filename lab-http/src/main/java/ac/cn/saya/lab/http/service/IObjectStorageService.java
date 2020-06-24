package ac.cn.saya.lab.http.service;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对象存储服务
 * @Title: IObjectStorageService
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description:对象存储
 */

public interface IObjectStorageService {

    /**
     * 上传动态、笔记图片服务
     * @param entity
     * @param request
     * @return
     */
    public Result<Object> updateNewsPicture(PictureEntity entity, HttpServletRequest request);

    /**
     * @描述 上传壁纸
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/13
     * @修改人和其它信息
     */
    public Result<Object> updateWallpaperPicture(MultipartFile file, HttpServletRequest request);


    /**
     * @描述 删除base64类型的图片
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<Object> deletePictuBase64(PictureEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页后的图片
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/13
     * @修改人和其它信息
     */
    public Result<Object> getPictuBase64List(PictureEntity entity, HttpServletRequest request);

    /**
     * @描述 获取单张图片信息
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/13
     * @修改人和其它信息
     */
    public Result<PictureEntity> getOnePictuBase64(PictureEntity entity, HttpServletRequest request);

    /**
     * @描述 上传文件
     * @参数  
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/15
     * @修改人和其它信息
     */
    public Result<Object> uploadFile(MultipartFile file, String uid, HttpServletRequest request);

    /**
     * @描述 修改文件信息
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/15
     * @修改人和其它信息
     */
    public Result<Object> editFileInfo(FilesEntity entity, HttpServletRequest request);

    /**
     * @描述 删除文件
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/15
     * @修改人和其它信息
     */
    public Result<Object> deleteFile(FilesEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页文件列表
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/15
     * @修改人和其它信息
     */
    public Result<Object> getFileList(FilesEntity entity, HttpServletRequest request);

    /**
     * @描述 下载文件
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/15
     * @修改人和其它信息
     */
    public Result<Object> downloadFileForAdmin(Integer id, HttpServletRequest request, HttpServletResponse response);


    /**
     * @描述 下载备份文件
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-03-02
     * @修改人和其它信息
     */
    public Result<Object> downloadBackUpDB(String archiveDate, HttpServletResponse response);

    /**
     * @描述 获取分页的备份数据库列表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-03-02
     * @修改人和其它信息
     */
    public Result<Object> getBackUpDBList(BackupLogEntity entity);

}
