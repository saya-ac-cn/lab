package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.FilesService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.FilesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: FilesServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-03-12 21:45
 * @Description:
 */

@Service("filesService")
public class FilesServiceImpl implements FilesService {


    private static Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Resource
    @Qualifier("filesDAO")
    private FilesDAO filesDAO;

    /***
     * @描述 添加文件上传记录
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertFile(FilesEntity entity) {
        try {
            return ResultUtil.success(filesDAO.insertFile(entity));
        } catch (Exception e) {
            logger.error("添加文件上传记录异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 保存修改文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> updateFile(FilesEntity entity) {
        try {
            return ResultUtil.success(filesDAO.updateFile(entity));
        } catch (Exception e) {
            logger.error("保存修改文件记录异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @param entity
     * @描述 删除文件记录
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteFile(FilesEntity entity) {
        try {
            return ResultUtil.success(filesDAO.deleteFile(entity));
        } catch (Exception e) {
            logger.error("删除文件记录异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @param entity
     * @描述 查询分页后的文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getFilePage(FilesEntity entity) {
        try {
            Long count = filesDAO.getFileCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> filesDAO.getFilePage((FilesEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询分页后的文件列表发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @param entity
     * @描述 获取一条文件信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<FilesEntity> getOneFile(FilesEntity entity) {
        try {
            return ResultUtil.success(filesDAO.getOneFile(entity));
        } catch (Exception e) {
            logger.error("获取一条文件信息异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
