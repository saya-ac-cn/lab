package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.service.medium.PlanService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: PlanController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:45
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * @描述 发布计划安排
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insertPlan(@RequestBody PlanEntity entity){
        return planService.insertPlan(entity);
    }

    /**
     * @描述 编辑修改计划安排
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Object> editPlan(@RequestBody PlanEntity entity){
        return planService.editPlan(entity);
    }

    /**
     * @描述 删除计划安排
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Object> deletePlan(PlanEntity entity){
        return planService.deletePlan(entity);
    }

    /***
     * @描述 查询一条计划安排
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<Object> getOnePlan(PlanEntity entity){
        return planService.getOnePlan(entity);
    }

    /**
     * @描述 获取计划安排列表
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/list")
    public Result<Object> getPlanList(PlanEntity entity){
        return planService.getPlanList(entity);
    }

    /**
     * @描述 获取当天的计划内容
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/today")
    public Result<Object> getTodayPlanList(){
        return planService.getTodayPlanList();
    }

}
