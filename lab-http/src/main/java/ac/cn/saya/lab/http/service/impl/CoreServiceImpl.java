package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.http.feignclient.UserFeignClient;
import ac.cn.saya.lab.http.service.ICoreService;
import ac.cn.saya.lab.api.bean.JwtOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Title: CoreServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description: 综合/核心数据库的相关服务
 */

@Service("coreServiceImpl")
public class CoreServiceImpl implements ICoreService {

    private static Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private JwtOperator jwtOperator;

    /**
     * 用户登录
     *
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> login(UserEntity user, HttpServletRequest request) throws Exception {
        HashMap<String, Object> userMap = new HashMap<>(3);
        userMap.put("account","saya");
        userMap.put("name","saya");
        userMap.put("ip","127.0.0.1");
        userMap.put("city","四川省成都市");

        String token = jwtOperator.generateToken(userMap);
        logger.info("用户：{}登录成功，生成的token={}，有效期：{}",userMap.get("name"),token,jwtOperator.getExpirationDateFromToken(token));
        return ResultUtil.success(token);
    }

    /**
     * 获取用户的信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getUserInfo(HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 注销
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> logout(HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @param request
     * @return
     */
    @Override
    public Result<Object> setUserInfo(UserEntity user, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @param request
     * @return
     */
    @Override
    public Result<Object> setPassword(UserEntity user, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 获取所有的日志类别
     *
     * @return
     */
    @Override
    public Result<Object> getLogType() throws Exception {
        return null;
    }

    /**
     * 查询日志
     * 按用户、类别、日期
     *
     * @param entity
     * @param request
     * @return
     */
    @Override
    public Result<Object> getLog(LogEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * 导出个人日志
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outLogExcel(LogEntity entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /**
     * @param imgBase64
     * @param request
     * @描述 上传logo
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateLogo(String imgBase64, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param fileName
     * @param request
     * @param response
     * @描述 下载文件 By 文件名
     * @参数 [fileName, request, response]
     * @返回值 void
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/6
     * @修改人和其它信息
     */
    @Override
    public void fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * @param date
     * @param request
     * @描述
     * @参数 [date, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询该月的计划
     */
    @Override
    public Result<Object> getPlan(String date, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询计划详情
     */
    @Override
    public Result<Object> getPlanDetail(PlanEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建计划
     */
    @Override
    public Result<Object> createPlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改计划
     */
    @Override
    public Result<Object> editPlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除计划
     */
    @Override
    public Result<Object> deletePlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询接口列表
     */
    @Override
    public Result<Object> getApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建接口
     */
    @Override
    public Result<Object> createApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改接口
     */
    @Override
    public Result<Object> editApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param entity
     * @param request
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除接口
     */
    @Override
    public Result<Object> deleteApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        return null;
    }

    /**
     * @param request
     * @描述 获取统计报表数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    @Override
    public Result<Object> dashBoard(HttpServletRequest request) throws Exception {
        return null;
    }
}
