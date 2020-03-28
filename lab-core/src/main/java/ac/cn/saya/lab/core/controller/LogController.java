package ac.cn.saya.lab.core.controller;

import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.entity.LogTypeEntity;
import ac.cn.saya.lab.api.service.core.LogService;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: LogController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-04 22:28
 * @Description:
 * 用户日志相关接口(内部)
 */
@RestController
@RequestMapping("/core/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * @描述 记录日志
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @PostMapping(value = "/record")
    public Result<Integer> insert(@RequestBody LogEntity entity){
        return logService.insert(entity);
    }

    /**
     * @描述 获取所有的日志类别
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogTypeEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/type")
    public Result<LogTypeEntity> selectLogType(){
        return logService.selectLogType();
    }

    /**
     * @描述 分页查询日志 按用户、类别、日期
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/display")
    public Result<Object> display(LogEntity entity){
        return logService.show(entity);
    }

    /**
     * @描述 查询用户最近的操作
     * @参数  [user]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-19
     * @修改人和其它信息
     */
    @GetMapping(value = "/recently")
    public Result<LogEntity> queryRecentlyLog(@RequestParam(value = "user") String user){
        return logService.queryRecentlyLog(user);
    }

    /**
     * @Title 获取日志总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "/count")
    public Result<Long> getCount(LogEntity entity){
        return logService.quertCount(entity);
    }

    /**
     * @Title 获取日志列表(分页) 需配合quertCount使用
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.LogEntity>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "/list")
    public Result<List<LogEntity>> getList(LogEntity entity){
        return logService.quertList(entity);
    }

}
