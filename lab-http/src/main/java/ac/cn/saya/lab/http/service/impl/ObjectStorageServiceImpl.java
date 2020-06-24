package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.BackFeignClient;
import ac.cn.saya.lab.http.feignclient.FilesFeignClient;
import ac.cn.saya.lab.http.feignclient.PictureStorageFeignClient;
import ac.cn.saya.lab.http.service.IObjectStorageService;
import ac.cn.saya.lab.http.tools.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Title: ObjectStorageServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/24 23:24
 * @Description:
 */
@Service
public class ObjectStorageServiceImpl implements IObjectStorageService {

    private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Resource
    @Qualifier("recordService")//日志助手表
    private RecordService recordService;

    @Resource
    private PictureStorageFeignClient pictureStorageFeignClient;

    @Resource
    private FilesFeignClient filesFeignClient;

    @Resource
    private BackFeignClient backFeignClient;

    /**
     * 上传动态、笔记图片服务
     *
     * @param entity
     * @param request
     * @return
     */
    @Override
    public Result<Object> updateNewsPicture(PictureEntity entity, HttpServletRequest request) {
        Result<String> upload = null;
        try {
            upload = UploadUtils.uploadPicture(entity.getFileurl(), "illustrated", request);
        } catch (Exception e) {
            // 图片上传异常
            throw new MyException(ResultEnum.ERROP);
        }
        if (upload.getCode() == 0) {
            //logo上传成功
            //得到文件上传成功的回传地址
            String successUrl = String.valueOf(upload.getData());
            //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
            UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
            entity.setSource(userSession.getUser());
            entity.setType(2);
            // 文件在服务器的存放目录
            entity.setFileurl(successUrl);
            // 浏览器可访问的url
            entity.setWeburl(UploadUtils.descUrl(successUrl));
            Result<Integer> result = pictureStorageFeignClient.uploadPictureBase64(entity);
            if (ResultUtil.checkSuccess(result)) {
                /**
                 * 记录日志
                 * 上传插图
                 */
                recordService.record("OX005", request);
                return ResultUtil.success(entity.getWeburl());
            } else {
                throw new MyException(ResultEnum.ERROP);
            }
        } else if (upload.getCode() == -2) {
            //不是有效的图片
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else {
            // 图片上传异常
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 上传壁纸
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateWallpaperPicture(MultipartFile file, HttpServletRequest request) {
        Result<String> upload = null;
        try {
            upload = UploadUtils.uploadWallpaper(file, request);
        } catch (Exception e) {
            throw new MyException(ResultEnum.ERROP);
        }
        if (upload.getCode() == 0) {
            //logo上传成功
            //得到文件上传成功的回传地址
            String successUrl = String.valueOf(upload.getData());
            //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
            UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
            PictureEntity entity = new PictureEntity();
            // 原文件名称
            entity.setFilename(file.getOriginalFilename());
            entity.setSource(userSession.getUser());
            entity.setType(1);
            // 文件在服务器的存放目录
            entity.setFileurl(successUrl);
            // 浏览器可访问的url
            entity.setWeburl(UploadUtils.descUrl(successUrl));
            Result<Integer> result = pictureStorageFeignClient.uploadPictureBase64(entity);
            if (ResultUtil.checkSuccess(result)){
                /**
                 * 记录日志
                 * 上传壁纸
                 */
                recordService.record("OX011", request);
                return ResultUtil.success(entity.getWeburl());
            } else {
                throw new MyException(ResultEnum.ERROP);
            }
        } else if (upload.getCode() == -2) {
            //不是有效的图片
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else if (upload.getCode() == -3) {
            //空文件
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else {
            // 图片上传异常
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除base64类型的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deletePictuBase64(PictureEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<PictureEntity> result = pictureStorageFeignClient.getOnePictuBase64(entity);
        PictureEntity picture = null;
        if (ResultUtil.checkSuccess(result) && !StringUtils.isEmpty((picture = result.getData()))){
            // 删除文件
            UploadUtils.deleteFile(picture.getFileurl());
            Result<Integer> deleteResult = pictureStorageFeignClient.deletePictuBase64(picture);
            if (ResultUtil.checkSuccess(deleteResult)) {
                /**
                 * 记录日志
                 */
                recordService.record("OX012", request);
                return ResultUtil.success();
            } else {
                throw new MyException(ResultEnum.ERROP);
            }
        }else {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        }
    }

    /**
     * @描述 获取分页后的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getPictuBase64List(PictureEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return pictureStorageFeignClient.getPictuBase64Page(entity);
    }

    /**
     * @描述 获取单张图片信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/13
     * @修改人和其它信息
     */
    @Override
    public Result<PictureEntity> getOnePictuBase64(PictureEntity entity, HttpServletRequest request) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<PictureEntity> result = pictureStorageFeignClient.getOnePictuBase64(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * @描述 上传文件
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/15
     * @修改人和其它信息
     */
    @Override
    public Result<Object> uploadFile(MultipartFile file, String uid, HttpServletRequest request) {
        Result<String> upload = null;
        try {
            upload = UploadUtils.uploadFile(file, request);
        } catch (Exception e) {
            throw new MyException(ResultEnum.ERROP);
        }
        if (upload.getCode() == 0) {
            //logo上传成功
            //得到文件上传成功的回传地址
            String successUrl = String.valueOf(upload.getData());
            //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
            UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
            FilesEntity entity = new FilesEntity();
            // 原文件名称
            entity.setFilename(file.getOriginalFilename());
            entity.setSource(userSession.getUser());
            entity.setStatus("2");
            // 文件在服务器的存放目录
            entity.setFileurl(successUrl);
            // 设置文件前端uid，方便删除
            if (StringUtils.isEmpty(uid)){
                entity.setUid("null");
            }else {
                entity.setUid(uid);
            }
            Result<Integer> result = filesFeignClient.insertFile(entity);
            if (ResultUtil.checkSuccess(result)) {
                /**
                 * 记录日志
                 * 上传文件
                 */
                recordService.record("OX013", request);
                return ResultUtil.success();
            } else {
                throw new MyException(ResultEnum.ERROP);
            }
        } else if (upload.getCode() == -2 || upload.getCode() == -3) {
            // 不是有效的文件
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else {
            // 文件上传异常
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改文件信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/15
     * @修改人和其它信息
     */
    @Override
    public Result<Object> editFileInfo(FilesEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = filesFeignClient.updateFile(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX015", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除文件
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/15
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteFile(FilesEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<FilesEntity> queryResult = filesFeignClient.getOneFile(entity);
        FilesEntity file = null;
        if (ResultUtil.checkSuccess(queryResult) && !StringUtils.isEmpty((file = queryResult.getData()))) {
            // 删除文件
            UploadUtils.deleteFile(file.getFileurl());
            Result<Integer> deleteResult = filesFeignClient.deleteFile(file);
            if (ResultUtil.checkSuccess(deleteResult)) {
                /**
                 * 记录日志
                 */
                recordService.record("OX014", request);
                return ResultUtil.success();
            } else {
                throw new MyException(ResultEnum.ERROP);
            }
        } else {
            // 未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        }
    }

    /**
     * @描述 获取分页文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/15
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getFileList(FilesEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
       return filesFeignClient.getFilePage(entity);

    }

    /**
     * @描述 下载文件
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/15
     * @修改人和其它信息
     */
    @Override
    public Result<Object> downloadFileForAdmin(Integer id, HttpServletRequest request, HttpServletResponse response) {
        if (id == null) {
            // 缺少参数
            response.setStatus(400);
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        FilesEntity queryEntity = new FilesEntity();
        queryEntity.setSource(userSession.getUser());
        queryEntity.setId(id);
        Result<FilesEntity> result = filesFeignClient.getOneFile(queryEntity);
        FilesEntity resultEntity = null;
        if (!ResultUtil.checkSuccess(result) || StringUtils.isEmpty((resultEntity = result.getData()).getFileurl())) {
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                File thisFile = UploadUtils.getFilePath(resultEntity.getFileurl());
                if (thisFile == null) {
                    // 文件不存在
                    response.setStatus(404);
                    throw new MyException(ResultEnum.NOT_EXIST);
                }
                fis = null;
                bis = null;
                os = null;
                //且仅当此对象抽象路径名表示的文件或目录存在时，返回true
                response.setContentType("application/x-download");
                //控制下载文件的名字
                response.addHeader("Content-Disposition", "attachment;filename=" + resultEntity.getFilename());
                byte buf[] = new byte[1024];
                fis = new FileInputStream(thisFile);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buf);
                while (i != -1) {
                    os.write(buf, 0, i);
                    i = bis.read(buf);
                }
                return ResultUtil.success();
            } catch (IOException e) {
                return ResultUtil.error(ResultEnum.ERROP);
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @描述 下载备份文件
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-02
     * @修改人和其它信息
     */
    @Override
    public Result<Object> downloadBackUpDB(String archiveDate, HttpServletResponse response) {
        if (StringUtils.isEmpty(archiveDate)) {
            // 缺少参数
            response.setStatus(400);
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        BackupLogEntity queryEntity = new BackupLogEntity();
        queryEntity.setArchiveDate(archiveDate);
        Result<BackupLogEntity> result = backFeignClient.getOneBackup(queryEntity);
        BackupLogEntity resultEntity = null;
        if (!ResultUtil.checkSuccess(result) || StringUtils.isEmpty((resultEntity = result.getData()).getUrl())) {
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                File thisFile = UploadUtils.getFilePath(resultEntity.getUrl());
                if (thisFile == null) {
                    // 文件不存在
                    response.setStatus(404);
                    throw new MyException(ResultEnum.NOT_EXIST);
                }
                fis = null;
                bis = null;
                os = null;
                //且仅当此对象抽象路径名表示的文件或目录存在时，返回true
                response.setContentType("application/x-download");
                //控制下载文件的名字
                response.addHeader("Content-Disposition", "attachment;filename=" + archiveDate + ".sql");
                byte buf[] = new byte[1024];
                fis = new FileInputStream(thisFile);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buf);
                while (i != -1) {
                    os.write(buf, 0, i);
                    i = bis.read(buf);
                }
                return ResultUtil.success();
            } catch (IOException e) {
                return ResultUtil.error(ResultEnum.ERROP);
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @描述 获取分页的备份数据库列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-02
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getBackUpDBList(BackupLogEntity entity) {
        return backFeignClient.getBackupPagin(entity);
    }
}
