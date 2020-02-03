package ac.cn.saya.lab.financial.service.impl;

import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.financial.TransactionReadService;
import ac.cn.saya.lab.api.tools.CurrentLineInfo;
import ac.cn.saya.lab.api.tools.Log4jUtils;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.financial.repository.TransactionReadDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TransactionReadServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/1 22:49
 * @Description:财政业务相关-对外只读业务层接口
 */
@Service
public class TransactionReadServiceImpl implements TransactionReadService {

    private static Logger logger = LoggerFactory.getLogger(TransactionReadServiceImpl.class);

    @Resource
    @Qualifier("transactionReadDAO")
    private TransactionReadDAO transactionReadDAO;

    /**
     * 获取所有交易类别数据
     *
     * @return
     */
    @Override
    public List<TransactionTypeEntity> selectTransactionType() {
        List<TransactionTypeEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionType();
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("查询交易类别时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionListEntity> selectTransactionPage(TransactionListEntity entity) {
        List<TransactionListEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("查看流水时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水总数
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionCount(TransactionListEntity entity) {
        try {
            return transactionReadDAO.selectTransactionCount(entity);
        } catch (Exception e) {
            logger.error("查看流水总数总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水明细
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionInfoEntity> selectTransactionInfoPage(TransactionInfoEntity entity) {
        List<TransactionInfoEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionInfoPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("查看流水明细发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水明细总数
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionInfoCount(TransactionInfoEntity entity) {
        try {
            return transactionReadDAO.selectTransactionInfoCount(entity);
        } catch (Exception e) {
            logger.error("查看查看流水明细总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查询详细的流水明细
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionInfoEntity> selectTransactionFinalPage(TransactionListEntity entity) {
        List<TransactionInfoEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionFinalPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("查询详细的流水明细发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查询详细的流水明细总数
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionFinalCount(TransactionListEntity entity) {
        try {
            return transactionReadDAO.selectTransactionFinalCount(entity);
        } catch (Exception e) {
            logger.error("查询详细的流水明细总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按天分页统计财务报表
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionListEntity> selectTransactionForDayPage(TransactionListEntity entity) {
        List<TransactionListEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionForDayPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("按天分页统计财务报表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按天统计财务报表流水总数
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionForDayCount(TransactionListEntity entity) {
        try {
            return transactionReadDAO.selectTransactionForDayCount(entity);
        } catch (Exception e) {
            logger.error("按天统计财务报表流水总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按月分页统计（只统计到上月的最后一天）
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionListEntity> selectTransactionForMonthPage(TransactionListEntity entity) {
        List<TransactionListEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionForMonthPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("按月分页统计（只统计到上月的最后一天）异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按月统计（只统计到上月的最后一天）总数
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionForMonthCount(TransactionListEntity entity) {
        try {
            return transactionReadDAO.selectTransactionForMonthCount(entity);
        } catch (Exception e) {
            logger.error("按月统计（只统计到上月的最后一天）总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按年分页统计（只统计到上一年的最后一天）
     *
     * @param entity
     * @return
     */
    @Override
    public List<TransactionListEntity> selectTransactionForYearPage(TransactionListEntity entity) {
        List<TransactionListEntity> list = new ArrayList<>();
        try {
            list = transactionReadDAO.selectTransactionForYearPage(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return list;
        } catch (Exception e) {
            logger.error("按年分页统计（只统计到上一年的最后一天）异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按年统计（只统计到上一年的最后一天）总数
     *
     * @param entity
     * @return
     */
    @Override
    public Long selectTransactionForYearCount(TransactionListEntity entity) {
        try {
            return transactionReadDAO.selectTransactionForYearCount(entity);
        } catch (Exception e) {
            logger.error("按年统计（只统计到上一年的最后一天）总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
