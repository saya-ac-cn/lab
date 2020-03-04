package ac.cn.saya.lab.core.service.impl;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.core.UserService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.core.repository.ProceDureDAO;
import ac.cn.saya.lab.core.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @描述 用户业务层实现类
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2018/11/11
 * @修改人和其它信息
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;


    /**
     * @描述 获取用户的信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getUser(String user) {
        try {
            return ResultUtil.success(userDAO.queryUser(user));
        } catch (Exception e) {
            logger.error("获取用户信息失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改用户信息
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> setUser(UserEntity user) {
        Integer result = 0;
        if (user == null || StringUtils.isEmpty(user.getUser())) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        try {
            result = userDAO.updateUser(user);
            if (result <= 0) {
                // 修改失败
                result = ResultEnum.ERROP.getCode();
            }
            return ResultUtil.success(result);
        } catch (Exception e) {
            logger.error("修改用户信息失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 查询近半年活跃情况
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    @Override
    public Result<Object> countPre6Logs(String user) {
        try {
            return ResultUtil.success(proceDureDAO.countPre6Logs(user));
        } catch (Exception e) {
            logger.error("查询近半年活跃情况失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


}
