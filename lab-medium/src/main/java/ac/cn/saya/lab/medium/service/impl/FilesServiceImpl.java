package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.FilesEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.FilesService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.FilesDAO;
import ac.cn.saya.lab.medium.repository.ProceDureDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Title: FilesServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-03-12 21:45
 * @Description:
 */

@Service("filesService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor=MyException.class)
public class FilesServiceImpl implements FilesService {


    private static Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Resource
    @Qualifier("filesDAO")
    private FilesDAO filesDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;


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
            Integer result = filesDAO.insertFile(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
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
            Integer result = filesDAO.updateFile(entity);
            if (result <= 0) {
                // 修改失败
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
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
            Integer result = filesDAO.deleteFile(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    @Override
    public Result<FilesEntity> getOneFile(FilesEntity entity) {
        try {
            FilesEntity result = filesDAO.getOneFile(entity);
            if (null != result){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("获取一条文件信息异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询近半年文件上传情况
     * @参数 [user]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Map<String,String>> countPre6Files(String user) {
        try {
            Map<String, Object> map = proceDureDAO.countPre6Files(user);
            if (!map.isEmpty()){
                return ResultUtil.success(map);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询近半年文件上传情况时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 统计文件总数
     * @Params [entity]
     * @Return ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author saya.ac.cn-刘能凯
     * @Date 2020-04-03
     * @Description
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Long> totalFileCount(FilesEntity entity) {
        try {
            Long count = filesDAO.getFileCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("统计文件总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
