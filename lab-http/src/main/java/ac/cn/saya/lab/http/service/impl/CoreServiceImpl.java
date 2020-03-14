package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.http.auth.RepeatLogin;
import ac.cn.saya.lab.http.entity.SecurityEntity;
import ac.cn.saya.lab.http.feignclient.LogFeignClient;
import ac.cn.saya.lab.http.feignclient.UserFeignClient;
import ac.cn.saya.lab.http.service.ICoreService;
import ac.cn.saya.lab.api.bean.JwtOperator;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import ac.cn.saya.lab.http.tools.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    private LogFeignClient logFeignClient;

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
        // 校验用户输入的参数
//        if (StringUtils.isEmpty(user.getUser()) || StringUtils.isEmpty(user.getPassword())) {
//            // 缺少参数
//            throw new MyException(ResultEnum.NOT_PARAMETER);
//        }
//        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
//        UserMemory userSession = HttpRequestUtil.getUserMemory(request);
//        UserEntity entity = userService.getUser(user.getUser());
//        if (entity == null) {
//            // 没有该用户的信息 直接中断返回
//            //未找到该用户
//            throw new MyException(ResultEnum.ERROP);
//        }
//        if (userSession != null) {
//            // 读取该用户最近的登录及安排信息
//            Map<String, Object> result = userService.queryUserRecentlyInfo(userSession.getUser());
//            // session 检查到已登录（同一机器设备中）
//            entity.setPassword(null);
//            // 转换成浏览器可以直接识别的url
//            // 用户logo，及背景图片的返回
//            entity.setLogo(UploadUtils.descUrl(entity.getLogo()));
//            entity.setBackground(UploadUtils.descUrl(entity.getBackground()));
//            // 注入用户个人信息
//            result.put("user",entity);
//            return ResultUtil.success(result);
//        } else {
//            // 未登录（当前设备）
//            // 在系统中查询该用户是否存在
//            // 在服务器全局中检查
//            // 由于登录时 可以用用户名 和 邮箱登录 所以 这里用用户查找
//            ///if(redisUtils.hmExists("DataCenter:SessionMap",entity.getUser())) {
//            if (RepeatLogin.securityMap.get(user.getUser()) != null) {
//                /**
//                 * 已经登录
//                 * 将已经登陆的信息拿掉,踢下线
//                 */
//                RepeatLogin.forceUserLogout(user.getUser());
//            }
//            // 比对密码
//            //加密后用户的密码
//            user.setPassword(DesUtil.encrypt(user.getPassword()));
//            if (entity.getPassword().equals(user.getPassword())) {
//                HttpSession session = request.getSession();
//                //设置session
//                // 设置封装到session中的实体信息
//                UserMemory memory = new UserMemory();
//                String ip = HttpRequestUtil.getIpAddress(request);
//                memory.setUser(entity.getUser());
//                memory.setIp(ip);
//                memory.setCity(amapLocateUtils.getCityByIp(ip));
//                //为之注入用户信息
//                session.setAttribute("user", memory);
//                //放入用户的session 到数据库中（或本服务器的内存中），防止重复登录
//                ///redisUtils.hmSet("DataCenter:SessionMap",user.getUser(),session.getId());
//                RepeatLogin.sessionMap.put(entity.getUser(), session);
//                // 对密码脱敏处理
//                entity.setPassword(null);
//                // 转换成浏览器可以直接识别的url
//                // 用户logo，及背景图片的返回
//                entity.setLogo(UploadUtils.descUrl(entity.getLogo()));
//                entity.setBackground(UploadUtils.descUrl(entity.getBackground()));
//                // 返回用户的个人信息
//                Map<String, Object> result = userService.queryUserRecentlyInfo(user.getUser());
//                result.put("user",entity);
//                // 记录本次登录
//                recordService.record("OX001", request);
//                //返回登录成功
//                return ResultUtil.success(result);
//            } else {
//                //密码错误
//                throw new MyException(ResultEnum.ERROP);
//            }
//
//        }


        HashMap<String, Object> userMap = new HashMap<>(3);
        userMap.put("user",user.getUser());
        userMap.put("ip","127.0.0.1");
        userMap.put("city","四川省成都市");

        String token = jwtOperator.generateToken(userMap);
        UserMemory memory = new UserMemory();
        String ip = HttpRequestUtil.getIpAddress(request);
        memory.setUser(user.getUser());
        memory.setIp(ip);
        memory.setCity("6666");
        HttpSession session = request.getSession();
        // 为之注入用户信息
        session.setAttribute("user", memory);
        //放入用户的session 到数据库中（或本服务器的内存中），防止重复登录
        RepeatLogin.securityMap.put(user.getUser(), new SecurityEntity(session,token));
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
