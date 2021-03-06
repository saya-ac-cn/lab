package ac.cn.saya.lab.http.service;


import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @Title: IFinancialService
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description: 综合/核心数据库的相关服务
 */

public interface ICoreService {

    /**
     * 用户登录
     * @param platform
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> login(String platform,UserEntity user, HttpServletRequest request);


    /**
     * 获取用户的信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> getUserInfo(HttpServletRequest request);

    /**
     * 注销
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Result<Object> logout(HttpServletRequest request);

    /**
     * 修改用户信息
     *
     * @param user
     * @param request
     * @return
     */
    public Result<Object> setUserInfo(UserEntity user, HttpServletRequest request);

    /**
     * 修改用户密码
     *
     * @param user
     * @param request
     * @return
     */
    public Result<Object> setPassword(UserEntity user, HttpServletRequest request);

    /**
     * 获取所有的日志类别
     *
     * @return
     */
    public Result<List<LogTypeEntity>> getLogType();

    /**
     * 查询日志
     * 按用户、类别、日期
     *
     * @param entity
     * @param request
     * @return
     */
    public Result<Object> getLog(LogEntity entity, HttpServletRequest request);

    /**
     * 导出个人日志
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Result<Object> outLogExcel(LogEntity entity, HttpServletRequest request, HttpServletResponse response);

    /**
     * @描述 上传logo
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    public Result<Object> updateLogo(String imgBase64, HttpServletRequest request);

    /**
     * @描述 下载文件 By 文件名
     * @参数 [fileName, request, response]
     * @返回值 void
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/6
     * @修改人和其它信息
     */
    public void fileDownload(String fileName, HttpServletRequest request, HttpServletResponse response);

    /**
     * @描述
     * @参数 [date, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询该月的计划
     */
    public Result<Object> getPlan(String date, HttpServletRequest request);

    /**
     * @描述
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询计划详情
     */
    public Result<PlanEntity> getPlanDetail(PlanEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建计划
     */
    public Result<Object> createPlan(PlanEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改计划
     */
    public Result<Object> editPlan(PlanEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除计划
     */
    public Result<Object> deletePlan(PlanEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询接口列表
     */
    public Result<Object> getApi(ApiEntity entity, HttpServletRequest request);


    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 创建接口
     */
    public Result<Object> createApi(ApiEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 修改接口
     */
    public Result<Object> editApi(ApiEntity entity, HttpServletRequest request);

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 删除接口
     */
    public Result<Object> deleteApi(ApiEntity entity, HttpServletRequest request);

    /**
     * @描述 获取统计报表数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    public Result<Object> dashBoard(HttpServletRequest request);


}
