package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.http.auth.RepeatLogin;
import ac.cn.saya.lab.http.entity.SecurityEntity;
import ac.cn.saya.lab.http.feignclient.*;
import ac.cn.saya.lab.http.service.ICoreService;
import ac.cn.saya.lab.api.bean.JwtOperator;
import ac.cn.saya.lab.http.tools.AmapLocateUtils;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import ac.cn.saya.lab.http.tools.OutExcelUtils;
import ac.cn.saya.lab.http.tools.UploadUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CoreServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description: 综合/核心数据库的相关服务
 */

@Service
public class CoreServiceImpl implements ICoreService {

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private LogFeignClient logFeignClient;

    @Resource
    private PlanFeignClient planFeignClient;

    @Resource
    private AmapLocateUtils amapLocateUtils;

    @Resource
    private RecordService recordService;

    @Resource
    private ApiFeignClient apiFeignClient;

    @Resource
    private PictureStorageFeignClient pictureStorageFeignClient;

    @Resource
    private NoteBookFeignClient noteBookFeignClient;

    @Resource
    private NotesFeignClient notesFeignClient;

    @Resource
    private FilesFeignClient filesFeignClient;

    @Resource
    private NewsFeignClient newsFeignClient;

    @Resource
    private MemoFeignClient memoFeignClient;

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
        if (StringUtils.isEmpty(user.getUser()) || StringUtils.isEmpty(user.getPassword())) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = HttpRequestUtil.getUserMemory(request);
        Result<UserEntity> userResult = userFeignClient.getUser(user.getUser());
        if (!ResultUtil.checkSuccess(userResult)) {
            // 没有该用户的信息 直接中断返回
            //未找到该用户
            throw new MyException(ResultEnum.ERROP);
        }
        UserEntity entity = userResult.getData();
        if (userSession != null) {
            // 读取该用户最近的登录及安排信息
            Result<PlanEntity> todayPlanResult = planFeignClient.getTodayPlanListByUser(userSession.getUser());
            Result<LogEntity> recentlyLogResult = logFeignClient.queryRecentlyLog(userSession.getUser());
            Map<String, Object> result = new HashMap<>(2);
            if (ResultUtil.checkSuccess(todayPlanResult) && ResultUtil.checkSuccess(recentlyLogResult)){
                result.put("plan",todayPlanResult.getData());
                result.put("log",recentlyLogResult.getData());
            }else {
                result.put("plan",null);
                result.put("log",null);
            }
            // session 检查到已登录（同一机器设备中）
            entity.setPassword(null);
            // 转换成浏览器可以直接识别的url
            // 用户logo，及背景图片的返回
            entity.setLogo(UploadUtils.descUrl(entity.getLogo()));
            entity.setBackground(UploadUtils.descUrl(entity.getBackground()));
            // 注入用户个人信息
            result.put("user",entity);
            return ResultUtil.success(result);
        } else {
            // 未登录（当前设备）
            // 在系统中查询该用户是否存在
            // 在服务器全局中检查
            // 由于登录时 可以用用户名 和 邮箱登录 所以 这里用用户查找
            ///if(redisUtils.hmExists("DataCenter:SessionMap",entity.getUser())) {
            if (RepeatLogin.securityMap.get(user.getUser()) != null) {
                /**
                 * 已经登录
                 * 将已经登陆的信息拿掉,踢下线
                 */
                RepeatLogin.forceUserLogout(user.getUser());
            }
            // 比对密码
            //加密后用户的密码
            user.setPassword(DesUtil.encrypt(user.getPassword()));
            if (entity.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                //设置session
                // 设置封装到session中的实体信息
                UserMemory memory = new UserMemory();
                String ip = HttpRequestUtil.getIpAddress(request);
                memory.setUser(entity.getUser());
                memory.setIp(ip);
                memory.setCity(amapLocateUtils.getCityByIp(ip));
                // 为用户生成token封装内容
                HashMap<String, Object> userMap = new HashMap<>(3);
                userMap.put("user",user.getUser());
                userMap.put("ip",ip);
                userMap.put("city",memory.getCity());
                // 生成token
                String token = jwtOperator.generateToken(userMap);
                //为之注入用户信息
                session.setAttribute("user", memory);
                //放入用户的session 到数据库中（或本服务器的内存中），防止重复登录
                ///redisUtils.hmSet("DataCenter:SessionMap",user.getUser(),session.getId());
                RepeatLogin.securityMap.put(user.getUser(), new SecurityEntity(session,token));
                // 对密码脱敏处理
                entity.setPassword(null);
                // 转换成浏览器可以直接识别的url
                // 用户logo，及背景图片的返回
                entity.setLogo(UploadUtils.descUrl(entity.getLogo()));
                entity.setBackground(UploadUtils.descUrl(entity.getBackground()));
                // 读取该用户最近的登录及安排信息
                Result<PlanEntity> todayPlanResult = planFeignClient.getTodayPlanListByUser(user.getUser());
                Result<LogEntity> recentlyLogResult = logFeignClient.queryRecentlyLog(user.getUser());
                Map<String, Object> result = new HashMap<>(2);
                if (ResultUtil.checkSuccess(todayPlanResult) && ResultUtil.checkSuccess(recentlyLogResult)){
                    result.put("log",recentlyLogResult.getData());
                    result.put("plan",todayPlanResult.getData());
                }else {
                    result.put("log",null);
                    result.put("plan",null);
                }
                result.put("user",entity);
                // 记录本次登录
                recordService.record("OX001", request);
                //返回登录成功
                return ResultUtil.success(result);
            } else {
                //密码错误
                throw new MyException(ResultEnum.ERROP);
            }

        }
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
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        // 在系统中查询该用户是否存在
        Result<UserEntity> userResult = userFeignClient.getUser(userSession.getUser());
        if (!ResultUtil.checkSuccess(userResult)) {
            // 没有该用户的信息 直接中断返回
            //未找到该用户
            throw new MyException(ResultEnum.ERROP);
        } else {
            UserEntity entity = userResult.getData();
            //脱敏用户密码
            entity.setPassword("");
            return ResultUtil.success(entity);
        }
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
        HttpSession session = request.getSession();
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) session.getAttribute("user");
        if (userSession != null) {
            // 删除缓存里面的登录信息
            RepeatLogin.delSession(session);
            session.invalidate();
        }
        return ResultUtil.success();
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
        // 校验用户输入的参数
        if (user == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        user.setUser(userSession.getUser());
        Result<Integer> result = userFeignClient.setUser(user);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            // 修改个人信息
            recordService.record("OX002", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
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
        // 校验用户输入的参数
        if (user == null || StringUtils.isEmpty(user.getPassword())) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        user.setUser(userSession.getUser());
        //加密后用户的密码
        user.setPassword(DesUtil.encrypt(user.getPassword()));
        Result<Integer> result = userFeignClient.setUser(user);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            // 修改密码
            recordService.record("OX004", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * 获取所有的日志类别
     *
     * @return
     */
    @Override
    public Result<LogTypeEntity> getLogType() throws Exception {
        Result<LogTypeEntity> result = logFeignClient.selectLogType();
        if (!ResultUtil.checkSuccess(result)) {
            // 没有该用户的信息 直接中断返回
            //未找到登录类别
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
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
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setUser(userSession.getUser());
        Result<Object> result = logFeignClient.display(entity);
        return result;
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
        String[] keys = {"user", "describe", "ip", "city", "date"};
        //放置到第一行的字段名
        String[] titles = {"用户", "操作详情", "IP", "城市", "日期"};
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        try {
            //获取满足条件的总记录（不分页）
            Result<Long> countResult = logFeignClient.getCount(entity);
            if (!ResultUtil.checkSuccess(countResult)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            Long count = countResult.getData();
            //设置行索引
            entity.setPage(0, count.intValue());
            entity.setUser(userSession.getUser());
            Result<List<LogEntity>> listResult = logFeignClient.getList(entity);
            if (!ResultUtil.checkSuccess(listResult)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //获取满足条件的记录集合
            List<LogEntity> entityList = listResult.getData();
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (LogEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("user", item.getUser());
                json.put("describe", item.getLogType().getDescribe());
                json.put("ip", item.getIp());
                json.put("city", item.getCity());
                json.put("date", item.getDate());
                jsonObjectList.add(json);
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("操作日志.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(keys, titles, jsonObjectList, out);
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title 上传logo
     * @Params  [imgBase64, request]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @Override
    public Result<Object> updateLogo(String imgBase64, HttpServletRequest request) throws Exception {
        Result<String> upload = UploadUtils.uploadLogo(imgBase64, request);
        if (upload.getCode() == 0) {
            //logo上传成功
            //得到文件上传成功的回传地址
            String successUrl = String.valueOf(upload.getData());
            //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
            UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
            UserEntity user = new UserEntity();
            user.setUser(userSession.getUser());
            user.setLogo(successUrl);
            Result<Integer> result = userFeignClient.setUser(user);
            if (ResultUtil.checkSuccess(result)) {
                /**
                 * 记录日志
                 * 上传头像
                 */
                recordService.record("OX003", request);
                return ResultUtil.success(UploadUtils.descUrl(successUrl));
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
     * @Title 下载文件 By 文件名
     * @Params  [fileName, request, response]
     * @Return  void
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @Override
    public void fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletContext().getRealPath(fileName);
        File filepath = new File(path);
        //获取要下载的文件名
        String[] fileArray = fileName.split(File.separator);
        String newFilename = fileArray[fileArray.length - 1];
        //判断文件父目录是否存在
        if (filepath.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 设置content-disposition响应头控制浏览器以下载的形式打开文件
            response.setHeader("Content-Disposition", "attachment;filename=" + newFilename);
            //创建数据缓冲区
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                //通过response对象获取OutputStream流
                os = response.getOutputStream();
                // 根据文件路径获取要下载的文件输入流
                bis = new BufferedInputStream(new FileInputStream(filepath));
                //将FileInputStream流写入到buffer缓冲区
                int i = bis.read(buff);
                while (i != -1) {
                    // 使用将OutputStream缓冲区的数据输出到客户端浏览器
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                response.setStatus(500);
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @Title 查询该月的计划
     * @Params  [date, request]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @Override
    public Result<Object> getPlan(String date, HttpServletRequest request) throws Exception {
        // 日期准备
        // 第一天
        String firstDay = DateUtils.getFirstDayOfMonth(date);
        // 最后一天
        String lastDay = DateUtils.getLastDayOfMonth(date);
        // 获取该月的总天数
        int monthCount = DateUtils.getLengthOfMonth(date);
        // 获取该月第一天是星期几--星期日i==1，星期六i==7
        int firstDayWeek = DateUtils.getFirstDayWeek(date);
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        PlanEntity query = new PlanEntity();
        // 查询该用户的计划
        query.setSource(userSession.getUser());
        // 查询该月的计划
        query.setBeginTime(firstDay);
        query.setEndTime(lastDay);
        if (firstDayWeek < 1 || firstDayWeek > 7) {
            // 获取的该月第一天是否正常
            throw new MyException(ResultEnum.ERROP);
        } else {
            // 表格中单元格个数（1号前的空单元格加上日历的单元格）
            Integer gridCount = monthCount + (firstDayWeek - 1);
            // 总行数
            Integer tableLine = 5;
            if (gridCount % 7 == 0) {
                tableLine = gridCount / 7;
            } else {
                tableLine = gridCount / 7 + 1;
            }
            // 统计有效的单元格（加上月尾的空白单元格）
            gridCount = tableLine * 7;
            List<JSONObject> jsonObjectList = new ArrayList<>();
            Result<List<PlanEntity>> result = planFeignClient.getPlanList(query);
            List<PlanEntity> plan = null;
            if (ResultUtil.checkSuccess(result)){
                plan = result.getData();
            }
            for (int i = 1; i <= gridCount; i++) {
                JSONObject json = new JSONObject();
                if (i >= firstDayWeek && i <= (monthCount + (firstDayWeek - 1))) {
                    json.put("flog", 1);
                    json.put("number", i - (firstDayWeek - 1));
                    json.put("value", 0);
                    json.put("id", -1);
                    // 输出日历
                    if (plan != null) {
                        for (PlanEntity item : plan) {
                            if (item.getNumber() == (i - (firstDayWeek - 1))) {
                                // 该天有内容
                                json.put("value", item.getDescribe());
                                json.put("id", item.getId());
                                break;
                            }
                        }
                    }
                } else {
                    // 输出空白
                    json.put("flog", 0);
                    json.put("number", 0);
                    json.put("value", 0);
                }
                jsonObjectList.add(json);
            }
            return ResultUtil.success(jsonObjectList);
        }
    }

    /**
     * @描述
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询计划详情
     */
    @Override
    public Result<PlanEntity> getPlanDetail(PlanEntity entity, HttpServletRequest request) throws Exception {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<PlanEntity> result = planFeignClient.getOnePlan(entity);
        return result;
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建计划
     */
    @Override
    public Result<Object> createPlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = planFeignClient.insertPlan(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX022", request);
            return ResultUtil.success();
        } else{
            // 该天计划已经存在
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改计划
     */
    @Override
    public Result<Object> editPlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        // 日期准备
        entity.setPlandate(DateUtils.getCurrentDateTime(DateUtils.dateFormat));
        Result<Integer> result = planFeignClient.editPlan(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX023", request);
            return ResultUtil.success();
        } else {
            // 该天计划已经存在
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除计划
     */
    @Override
    public Result<Object> deletePlan(PlanEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = planFeignClient.deletePlan(entity);
        if (ResultUtil.checkSuccess(result))  {
            /**
             * 记录日志
             */
            recordService.record("OX024", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询接口列表
     */
    @Override
    public Result<Object> getApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        return apiFeignClient.getApiPage(entity);
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建接口
     */
    @Override
    public Result<Object> createApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = apiFeignClient.insertApi(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX031", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改接口
     */
    @Override
    public Result<Object> editApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = apiFeignClient.editApi(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX032", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除接口
     */
    @Override
    public Result<Object> deleteApi(ApiEntity entity, HttpServletRequest request) throws Exception {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = apiFeignClient.deleteApi(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX033", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 获取统计报表数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    @Override
    public Result<Object> dashBoard(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>(20);
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        // 统计图片总数
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setSource(userSession.getUser());
        Long pictureCount = ResultUtil.extractLong(pictureStorageFeignClient.getPictuBase64Count(pictureEntity));
        result.put("pictureCount", pictureCount);
        // 统计文件总数
        FilesEntity filesEntity = new FilesEntity();
        filesEntity.setSource(userSession.getUser());
        Long fileCount = ResultUtil.extractLong(filesFeignClient.totalFileCount(filesEntity));
        result.put("fileCount", fileCount);
        // 统计笔记簿总数
        NoteBookEntity bookEntity = new NoteBookEntity();
        bookEntity.setSource(userSession.getUser());
        Long bookCount = ResultUtil.extractLong(noteBookFeignClient.totalNoteBookCount(bookEntity));
        result.put("bookCount", bookCount);
        // 统计笔记总数
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setNotebook(bookEntity);
        Long notesCount = ResultUtil.extractLong(notesFeignClient.totalNotesCount(notesEntity));
        result.put("notesCount", notesCount);
        // 统计计划总数
        PlanEntity planEntity = new PlanEntity();
        planEntity.setSource(userSession.getUser());
        Long planCount = ResultUtil.extractLong(planFeignClient.totalPlanCount(planEntity));
        result.put("planCount", planCount);
        // 统计公告总数
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setSource(userSession.getUser());
        Long newsCount = ResultUtil.extractLong(newsFeignClient.totalNewsCount(newsEntity));
        result.put("newsCount", newsCount);
        // 统计便笺总数
        MemoEntity memoEntity = new MemoEntity();
        memoEntity.setSource(userSession.getUser());
        Long guestCount = ResultUtil.extractLong(memoFeignClient.totalCount(memoEntity));
        result.put("memoCount", guestCount);
        // 统计登录总数
        LogEntity logEntity = new LogEntity();
        logEntity.setUser(userSession.getUser());
        logEntity.setType("OX001");
        Long logCount = ResultUtil.extractLong(logFeignClient.getCount(logEntity));
        result.put("logCount", logCount);
        // 统计笔记簿
        bookEntity.setStartLine(0);
        bookEntity.setEndLine(bookCount.intValue());
        result.put("bookList", ResultUtil.extractList(noteBookFeignClient.getNoteBook(bookEntity)));
        result.put("news6", ResultUtil.extractList(userFeignClient.countPre6Logs(userSession.getUser())));
        result.put("log6", ResultUtil.extractList(userFeignClient.countPre6Logs(userSession.getUser())));
        result.put("files6", ResultUtil.extractList(filesFeignClient.countPre6Files(userSession.getUser())));
        result.put("memo", ResultUtil.extractList(memoFeignClient.countPre6Memo(userSession.getUser())));
        result.put("financial6", ResultUtil.extractList(filesFeignClient.countPre6Files(userSession.getUser())));
        return ResultUtil.success(result);
    }
}
