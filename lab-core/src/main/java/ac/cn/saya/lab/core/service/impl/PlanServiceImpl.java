package ac.cn.saya.lab.core.service.impl;

import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.core.PlanService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.core.repository.PlanDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: PlanServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 23:13
 * @Description: 行程计划对外服务接口
 */

@Service("planService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor=MyException.class)
public class PlanServiceImpl implements PlanService {

    private static Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Resource
    @Qualifier("planDAO")
    private PlanDAO planDAO;

    /**
     * @描述 发布计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertPlan(PlanEntity entity) {
        try {
            List<PlanEntity> list = planDAO.getPlanList(entity);
            if (list.size() <= 0) {
                Integer flog = planDAO.insertPlan(entity);
                if (flog > 0){
                    return ResultUtil.success(flog);
                }
                return ResultUtil.error(ResultEnum.DB_ERROR);
            } else {
                // 该天的计划已经存在
                return ResultUtil.error(ResultEnum.ERROP);
            }
        } catch (Exception e) {
            logger.error("发布计划安排异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 编辑修改计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editPlan(PlanEntity entity) {
        try {
            Integer flog = null;
            PlanEntity query = new PlanEntity();
            // 判断用户的计划日期是否发生变更
            query.setId(entity.getId());
            PlanEntity oldEntity = planDAO.getOnePlan(query);
            if (oldEntity == null) {
                // 未找到原值，不允许修改
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            } else {
                if (oldEntity.getPlandate().trim().equals(entity.getPlandate().trim())) {
                    // 用户未修改日期
                    flog = planDAO.updatePlan(entity);
                    if (flog>0){
                        return ResultUtil.success(flog);
                    }
                    return ResultUtil.error(ResultEnum.DB_ERROR);
                } else {
                    // 用户已经修改日期
                    // 必须清除上一步的查询条件
                    query.setId(null);
                    query.setPlandate(entity.getPlandate());
                    List<PlanEntity> list = planDAO.getPlanList(entity);
                    if (list.size() > 0) {
                        // 该天已存在计划
                        return ResultUtil.error(ResultEnum.ERROP);
                    } else {
                        flog = planDAO.updatePlan(entity);
                        if (flog>0){
                            return ResultUtil.success(flog);
                        }
                        return ResultUtil.error(ResultEnum.DB_ERROR);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("编辑修改计划安排异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除计划安排
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deletePlan(PlanEntity entity) {
        try {
            Integer result = planDAO.deletePlan(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除计划安排异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条计划安排
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.PlanEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<PlanEntity> getOnePlan(PlanEntity entity) {
        try {
            PlanEntity result = planDAO.getOnePlan(entity);
            if (null != result){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询一条计划安排异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取计划安排列表(不分页)
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<List<PlanEntity>> getPlanList(PlanEntity entity) {
        try {
            List<PlanEntity> list = planDAO.getPlanList(entity);
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("获取计划安排列表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 获取当天的计划内容
     * @参数  []
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.List<ac.cn.saya.lab.api.entity.PlanEntity>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020/6/25
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<List<PlanEntity>> getTodayPlanList() {
        try {
            List<PlanEntity> list = planDAO.getTodayPlanList();
            if (list.size() <= 0) {
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("获取当天的计划内容异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取指定用户当天的计划内容
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-15
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<PlanEntity> getTodayPlanListByUser(String source) {
        try {
            // 查询用户当日安排
            List<PlanEntity> result = planDAO.getTodayPlanListByUser(source);
            if (!result.isEmpty()){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询用户当日安排失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 获取计划总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-04
     * @Description
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Long> totalPlanCount(PlanEntity entity){
        try {
            Long count = planDAO.getPlanCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("获取计划总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
