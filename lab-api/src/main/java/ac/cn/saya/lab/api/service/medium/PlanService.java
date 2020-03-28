package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.List;

/**
 * @Title: PlanService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:46
 * @Description:
 */

public interface PlanService {

    /**
     * @描述 发布计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> insertPlan(PlanEntity entity);

    /**
     * @描述 编辑修改计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> editPlan(PlanEntity entity);

    /**
     * @描述 删除计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> deletePlan(PlanEntity entity);

    /**
     * @描述 查询一条计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.PlanEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<PlanEntity> getOnePlan(PlanEntity entity);

    /**
     * @Title 获取计划安排列表
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.PlanEntity>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    public Result<List<PlanEntity>> getPlanList(PlanEntity entity);

    /**
     * @描述 获取当天的计划内容
     * @参数 []
     * @返回值 java.util.List<ac.cn.saya.lab.api.entity.PlanEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<PlanEntity> getTodayPlanList();

    /**
     * @描述 获取指定用户当天的计划内容
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-15
     * @修改人和其它信息
     */
    public Result<PlanEntity> getTodayPlanListByUser(String source);
}
