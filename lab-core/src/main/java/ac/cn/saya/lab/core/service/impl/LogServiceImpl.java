package ac.cn.saya.lab.core.service.impl;

import ac.cn.saya.lab.api.service.core.LogService;
import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.entity.LogTypeEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.core.repository.LogDAO;
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
 * @创建时间 2018/11/11
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
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> insert(LogEntity entity) {
        Integer flog = null;
        try {
            flog = logDAO.insert(entity);
            if (flog > 0) {
                // 返回自增后的主键
                flog = entity.getId();
            } else {
                //插入失败
                flog = ResultEnum.ERROP.getCode();
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
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> selectLogType() {
        List<LogTypeEntity> list = new ArrayList<>();
        try {
            list = logDAO.selectType();
            if (list.size() <= 0) {
                list = null;
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
     * @创建时间 2018/11/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> show(LogEntity entity) {
        Paging paging = new Paging();
        if (entity.getNowPage() == null) {
            entity.setNowPage(1);
        }
        if (entity.getPageSize() == null) {
            entity.setPageSize(20);
        }
        //每页显示记录的数量
        paging.setPageSize(entity.getPageSize());
        //获取满足条件的总记录（不分页）
        Long pageSize = logDAO.selectCount(entity);
        if (pageSize > 0) {
            //总记录数
            paging.setDateSum(pageSize);
            //计算总页数
            paging.setTotalPage();
            //设置当前的页码-并校验是否超出页码范围
            paging.setPageNow(entity.getNowPage());
            //设置行索引
            entity.setPage((paging.getPageNow() - 1) * paging.getPageSize(), paging.getPageSize());
            //获取满足条件的记录集合
            List<LogEntity> list = logDAO.selectPage(entity);
            paging.setGrid(list);
            return ResultUtil.success(paging);
        } else {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        }
    }

}
