package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.service.medium.ApiService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.api.entity.ApiEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.medium.repository.ApiDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: NotesServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-12 20:52
 * @Description: 对外接口实现类
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {

    private static Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Resource
    @Qualifier("notesDAO")
    private ApiDAO apiDAO;

    /**
     * @描述 添加接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> insertApi(ApiEntity entity) {
        try {
            return ResultUtil.success(apiDAO.insertApi(entity));
        } catch (Exception e) {
            logger.error("添加接口异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 编辑接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> editApi(ApiEntity entity) {
        try {
            return ResultUtil.success(apiDAO.updateApi(entity));
        } catch (Exception e) {
            logger.error("编辑接口异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteApi(ApiEntity entity) {
        try {
            return ResultUtil.success(apiDAO.deleteApi(entity));
        } catch (Exception e) {
            logger.error("删除接口异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条接口信息
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.ApiEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOneApi(ApiEntity entity) {
        try {
            return ResultUtil.success(apiDAO.getOneApi(entity));
        } catch (Exception e) {
            logger.error("查询一条接口信息异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getApiPage(ApiEntity entity) {
        try {
            Long count = apiDAO.getApiCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> apiDAO.getApiPage((ApiEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的接口发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
    
}
