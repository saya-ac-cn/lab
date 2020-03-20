package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: PlanFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:45
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "plan")
public interface PlanFeignClient {


    /**
     * @描述 发布计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/plan")
    public Result<Integer> insertPlan(@RequestBody PlanEntity entity);

    /**
     * @描述 编辑修改计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/plan")
    public Result<Integer> editPlan(@RequestBody PlanEntity entity);

    /**
     * @描述 删除计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/plan")
    public Result<Integer> deletePlan(PlanEntity entity);

    /***
     * @描述 查询一条计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.PlanEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/plan/one")
    public Result<PlanEntity> getOnePlan(PlanEntity entity);

    /**
     * @描述 获取计划安排列表
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/plan/list")
    public Result<Object> getPlanList(PlanEntity entity);

    /**
     * @描述 获取当天的计划内容
     * @参数 []
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.PlanEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/plan/today")
    public Result<PlanEntity> getTodayPlanList();

    /**
     * @描述 获取指定用户当天的计划内容
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-15
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/plan/user/today")
    public Result<PlanEntity> getTodayPlanListByUser(@RequestParam(value = "source") String source);

}
