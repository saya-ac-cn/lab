package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.http.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: SetController
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/10/6 14:34
 * @Description: 个人设置控制器
 */
@RestController
@RequestMapping(value = "/backend/api/set")
public class SetController {

    @Autowired
    private ICoreService coreServiceImpl;

    /**
     * 获取用户的个人信息
     *
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @GetMapping(value = "personal")
    public Result<Object> getPersonal(HttpServletRequest httpServletRequest){
        return coreServiceImpl.getUserInfo(httpServletRequest);
    }

    /**
     * 修改用户信息
     *
     * @param entity
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @PutMapping(value = "update")
    public Result<Object> setInfo(@RequestBody UserEntity entity, HttpServletRequest httpServletRequest){
        return coreServiceImpl.setUserInfo(entity, httpServletRequest);
    }

    /**
     * 修改密码
     *
     * @param entity
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @PutMapping(value = "password")
    public Result<Object> setPassword(@RequestBody UserEntity entity, HttpServletRequest httpServletRequest){
        return coreServiceImpl.setPassword(entity, httpServletRequest);
    }

    /**
     * 获取所有的日志类别
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "logtype")
    public Result<List<LogTypeEntity>> getLogType(HttpServletRequest httpServletRequest){
        return coreServiceImpl.getLogType();
    }

    /**
     * 获取本账户下的所有日志
     *
     * @param entity
     * @param httpServletRequest
     * @return
     * @throws Exception 必须为Post请求
     *                   请求主体必须是RequestBody
     *                   否则无法接收到参数
     */
    @PostMapping(value = "log")
    public Result<Object> getLog(@RequestBody LogEntity entity, HttpServletRequest httpServletRequest){
        return coreServiceImpl.getLog(entity, httpServletRequest);
    }

    /**
     * 导出日志到excel
     *
     * @param entity
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @GetMapping(value = "log/excel")
    public Result<Object> logExcel(LogEntity entity, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return coreServiceImpl.outLogExcel(entity, httpServletRequest, httpServletResponse);
    }

    /**
     * @描述
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 上传logo
     */
    @PostMapping(value = "uploadlogo")
    public Result<Object> uploadlogo(@RequestBody UserEntity entity, HttpServletRequest request){
        return coreServiceImpl.updateLogo(entity.getLogo(), request);
    }

    /**
     * @描述 创建计划
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @PostMapping(value = "plan/create")
    public Result<Object> createPlan(@RequestBody PlanEntity entity, HttpServletRequest request){
        return coreServiceImpl.createPlan(entity, request);
    }

    /**
     * @描述 编辑计划
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "plan/edit")
    public Result<Object> editPlan(@RequestBody PlanEntity entity, HttpServletRequest request){
        return coreServiceImpl.editPlan(entity, request);
    }

    /**
     * @描述
     * @参数 删除计划
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "plan/delete")
    public Result<Object> deletePlan(PlanEntity entity, HttpServletRequest request){
        return coreServiceImpl.deletePlan(entity, request);
    }

    /**
     * @描述 获取该月计划
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "plan")
    public Result<Object> getPlan(@RequestParam(value = "date") String date, HttpServletRequest request){
        return coreServiceImpl.getPlan(date, request);
    }

    /**
     * @描述 查询计划详情
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "plan/show")
    public Result<PlanEntity> getOneNotes(PlanEntity entity, HttpServletRequest request){
        return coreServiceImpl.getPlanDetail(entity, request);
    }

    /**
     * 分页查看接口列表
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "api/list")
    public Result<Object> getApi(ApiEntity entity, HttpServletRequest request){
        return coreServiceImpl.getApi(entity, request);
    }

    /**
     * @描述 创建接口
     * @参数 [entity, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @PostMapping(value = "api/create")
    public Result<Object> createApi(@RequestBody ApiEntity entity, HttpServletRequest request){
        return coreServiceImpl.createApi(entity, request);
    }

    /**
     * @描述 编辑接口
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "api/edit")
    public Result<Object> editApi(@RequestBody ApiEntity entity, HttpServletRequest request){
        return coreServiceImpl.editApi(entity, request);
    }

    /**
     * @描述
     * @参数 删除接口
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "api/delete")
    public Result<Object> deleteApi(ApiEntity entity, HttpServletRequest request){
        return coreServiceImpl.deleteApi(entity, request);
    }

    /**
     * @描述 获取控制台数据
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-03
     * @修改人和其它信息
     */
    @GetMapping(value = "dashBoard")
    public Result<Object> dashBoard(HttpServletRequest request){
        return coreServiceImpl.dashBoard(request);
    }


}
