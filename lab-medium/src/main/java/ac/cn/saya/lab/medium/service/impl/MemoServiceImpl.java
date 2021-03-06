package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.MemoService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.MemoDAO;
import ac.cn.saya.lab.medium.repository.ProceDureDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Title: MemoServiceImpl
 * @ProjectName laboratory
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020/3/12 21:13
 * @Description: 便笺对外接口
 */
@Service("memoService")
@Transactional(readOnly = false,propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE, rollbackFor=MyException.class)
public class MemoServiceImpl implements MemoService {

    private static Logger logger = LoggerFactory.getLogger(MemoServiceImpl.class);

    @Resource
    @Qualifier("memoDAO")
    private MemoDAO memoDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insert(MemoEntity entity) {
        try {
            if (entity == null){
                return ResultUtil.error(ResultEnum.NOT_PARAMETER);
            }
            // 进行加密
            entity.setContent(AesUtil.Encrypt(entity.getContent()));
            int result = memoDAO.insert(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("创建便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 查询便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<MemoEntity> getOne(MemoEntity entity) {
        try {
            MemoEntity result = memoDAO.query(entity);
            if (null != result && !StringUtils.isEmpty(result.getContent())) {
                result.setContent(AesUtil.Decrypt(result.getContent()));
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> update(MemoEntity entity) {
        try {
            if (entity == null){
                return ResultUtil.error(ResultEnum.NOT_PARAMETER);
            }
            // 加密处理
            entity.setContent(AesUtil.Encrypt(entity.getContent()));
            int result = memoDAO.update(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("修改便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> delete(MemoEntity entity) {
        try {
            int result = memoDAO.delete(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除便笺异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Object> getPage(MemoEntity entity) {
        try {
            Long count = memoDAO.queryCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> memoDAO.queryPage((MemoEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的便笺列表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 统计便笺总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Long> totalCount(MemoEntity entity){
        try {
            Long count = memoDAO.queryCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("统计便笺总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 查询近半年留言情况
     * @Params  [user]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-08
     * @Description
     */
    @Transactional(readOnly = true)
    @Override
    public Result<Map<String,String>> countPre6Memo(String user) {
        try {
            Map<String, Object> map = proceDureDAO.countPre6Memo(user);
            if (!map.isEmpty()){
                return ResultUtil.success(map);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询近半年留言情况时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
