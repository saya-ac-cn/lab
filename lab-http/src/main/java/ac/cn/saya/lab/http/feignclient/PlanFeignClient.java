package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: PlanFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:45
 * @Description:
 */
@FeignClient(value = "lab-core-server", contextId = "plan",path = "core/plan")
public interface PlanFeignClient {


    /**
     * @描述 发布计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insertPlan(@RequestBody PlanEntity entity);

    /**
     * @描述 编辑修改计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> editPlan(@RequestBody PlanEntity entity);

    /**
     * @描述 删除计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deletePlan(@RequestBody PlanEntity entity);

    /***
     * @描述 查询一条计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.PlanEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<PlanEntity> getOnePlan(@RequestBody PlanEntity entity);

    /***
     * @Title 获取计划安排列表
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.PlanEntity>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "list",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PlanEntity>> getPlanList(@RequestBody PlanEntity entity);

    /**
     * @描述 获取当天的计划内容
     * @参数 []
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.PlanEntity>>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "today")
    public Result<List<PlanEntity>> getTodayPlanList();

    /**
     * @描述 获取指定用户当天的计划内容
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-15
     * @修改人和其它信息
     */
    @GetMapping(value = "user/today")
    public Result<PlanEntity> getTodayPlanListByUser(@RequestParam(value = "source") String source);


    /**
     * @Title 获取计划总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-04
     * @Description
     */
    @GetMapping(value = "totalPlanCount")
    public Result<Long> totalPlanCount(@RequestBody PlanEntity entity);

}
