package ac.cn.saya.lab.core.service.impl;

import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.entity.LogTypeEntity;
import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.core.LogService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.core.repository.LogDAO;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述 用户业务层实现类
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2020/3/13
 * @修改人和其它信息 增加备注：
 * 注意MySQL表中的数据库引擎必须是InnoDB，否则不会生效
 * propagation=Propagation.REQUIRED  传播行为：支持当前事务，如果当前没有事务，就新建一个事务
 * isolation = Isolation.READ_COMMITTED 隔离级别：读写提交
 * rollbackFor=Exception.class 发送异常时回滚：回滚全部
 */

@Service
public class LogServiceImpl implements LogService {

    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    @Qualifier("logDAO")
    private LogDAO logDAO;

    /**
     * @描述 插入日志
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insert(LogEntity entity) {
        Integer flog = null;
        try {
            flog = logDAO.insert(entity);
            if (flog > 0) {
                // 返回自增后的主键
                flog = entity.getId();
            } else {
                //插入失败
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success(flog);
        } catch (Exception e) {
            logger.error("插入日志时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取所有的日志类别
     * @参数 []
     * @返回值 java.util.List<ac.cn.saya.datacenter.entity.LogTypeEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<List<LogTypeEntity>> selectLogType() {
        List<LogTypeEntity> list;
        try {
            list = logDAO.selectType();
            if (list.size() <= 0) {
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("查询日志类别时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 分页查询日志 按用户、类别、日期
     * @参数 [entity]
     * @返回值 java.util.List<ac.cn.saya.datacenter.entity.LogEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> show(LogEntity entity) {
        try {
            Long count = logDAO.selectCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> logDAO.selectPage((LogEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("分页查询日志时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询用户最近的操作
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-15
     * @修改人和其它信息
     */
    @Override
    public Result<LogEntity> queryRecentlyLog(String user) {
        try {
            // 查询用户当日安排
            LogEntity log = logDAO.queryRecentlyLog(user);
            if (null == log) {
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(log);
        } catch (Exception e) {
            logger.error("查询用户最近的操作失败" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 导出日志到excel
     * @param entity
     * @return
     */
    @Override
    public Result<OutExcelEntity> outLogExcel(LogEntity entity){
        String[] keys = {"user", "describe", "ip", "city", "date"};
        //放置到第一行的字段名
        String[] titles = {"用户", "操作详情", "IP", "城市", "日期"};
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = logDAO.selectCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<LogEntity> entityList = logDAO.selectPage(entity);
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
            OutExcelEntity outExcel = new OutExcelEntity();
            outExcel.setKeys(keys);
            outExcel.setTitles(titles);
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("导出日志到excel异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }


}
