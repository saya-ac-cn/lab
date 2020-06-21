package ac.cn.saya.lab.financial.service.impl;

import ac.cn.saya.lab.api.entity.OutExcelEntity;
import ac.cn.saya.lab.api.entity.TransactionInfoEntity;
import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.entity.TransactionTypeEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.financial.IFinancialService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.financial.repository.FinancialDeclareService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: FinancialServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/4 17:13
 * @Description: 财政数据提供的相关服务
 */
@Service("financialServiceImpl")
public class FinancialServiceImpl implements IFinancialService {

    private static Logger logger = LoggerFactory.getLogger(FinancialServiceImpl.class);

    @Resource
    @Qualifier("financialDeclareService")
    private FinancialDeclareService financialDeclareService;

    /**
     * 查询近半年财政收支情况
     * @param user
     * @return
     */
    @Override
    public Result<List<TransactionListEntity>> countPre6Financial(String user){
        try {
            List<TransactionListEntity> list = financialDeclareService.countPre6Financial(user);
            if (list == null || list.size() < 0) {
                // 没有找到交易类别
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            } else {
                return ResultUtil.success(list);
            }
        } catch (Exception e) {
            logger.error("查询近半年财政收支情况时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 获取所有的交易类别
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<List<TransactionTypeEntity>> getTransactionType() {
        List<TransactionTypeEntity> list = null;
        try {
            list = financialDeclareService.selectTransactionType();
            if (list == null || list.size() < 0) {
                // 没有找到交易类别
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            } else {
                return ResultUtil.success(list);
            }
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水（这里不是明细）
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransaction(TransactionListEntity entity) {
        try {
            Long count = financialDeclareService.selectTransactionCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionPage((TransactionListEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查看流水子明细
     * 根据父id，本位id，flog
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionInfo(TransactionInfoEntity entity) {
        try {
            Long count = financialDeclareService.selectTransactionInfoCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionInfoPage((TransactionInfoEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 查询详细的流水明细总数
     * 根据用户、类型、日期
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionFinal(TransactionListEntity entity){
        try {
            Long count = financialDeclareService.selectTransactionFinalCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionFinalPage((TransactionListEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransaction(TransactionListEntity entity){
        try {
            Result<Object> result = financialDeclareService.insertTransaction(entity);
            if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
                // recordService.record("OX025", request);
               return ResultUtil.error(ResultEnum.ERROP);
            }
            return result;
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 修改财政记录父
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransaction(TransactionListEntity entity) {
        // 只允许修改交易类别以及交易摘要
        if (entity == null || entity.getTradeId() == null || entity.getTradeType() == null || StringUtils.isEmpty(entity.getTransactionAmount())) {
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else {
            Result<Object> result = null;
            try {
                result = financialDeclareService.updateTransaction(entity);
            } catch (Exception e) {
                throw new MyException(ResultEnum.DB_ERROR);
            }
            if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
                // recordService.record("OX026", request);
                return ResultUtil.error(ResultEnum.ERROP);
            }
            return result;
        }
    }

    /**
     * 这里是级联删除
     * 删除财政记录父+子
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransaction(TransactionListEntity entity){
        if (entity == null || entity.getTradeId() == null) {
            throw new MyException(ResultEnum.NOT_PARAMETER);
        } else {
            Result<Object> result = null;
            try {
                result = financialDeclareService.deleteTransaction(entity, entity.getSource());
            } catch (Exception e) {
                throw new MyException(ResultEnum.DB_ERROR);
            }
            if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
                /**
                 * 删除流水
                 */
                // recordService.record("OX027", request);
                return ResultUtil.error(ResultEnum.ERROP);
            }
            return result;
        }
    }

    /**
     * 单行
     * 添加财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransactionInfo(TransactionInfoEntity entity, String user){
        Result<Object> result = null;
        try {
            result = financialDeclareService.insertTransactioninfo(entity, user);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
        if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
            /**
             * 删除流水
             */
            // recordService.record("OX028", request);
            return ResultUtil.error(ResultEnum.ERROP);
        }
        return result;
    }

    /**
     * 单行
     * 修改财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransactionInfo(TransactionInfoEntity entity, String user) {
        Result<Object> result = null;
        try {
            result = financialDeclareService.updateTransactioninfo(entity, user);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
        if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
            // recordService.record("OX029", request);
            return ResultUtil.error(ResultEnum.ERROP);
        }
        return result;
    }

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransactionInfo(TransactionInfoEntity entity, String user) {
        Result<Object> result = null;
        try {
            result = financialDeclareService.deleteTransactioninfo(entity, user);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
        if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
            // recordService.record("OX030", request);
            return ResultUtil.error(ResultEnum.ERROP);
        }
        return result;
    }

    /**
     * 导出流水
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<OutExcelEntity> outTransactionListExcel(TransactionListEntity entity) {
        String[] keys = {"tradeId", "deposited", "expenditure", "transactionType", "currencyNumber", "tradeDate", "transactionAmount", "createTime", "updateTime"};
        //放置到第一行的字段名
        String[] titles = {"流水号", "存入", "取出", "交易方式", "产生总额", "产生日期", "摘要", "创建时间", "修改时间"};
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = financialDeclareService.selectTransactionCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<TransactionListEntity> entityList = financialDeclareService.selectTransactionPage(entity);
            List<JSONObject> jsonObjectList = new ArrayList<>(pageSize.intValue());
            for (TransactionListEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("tradeId", item.getTradeId());
                json.put("deposited", item.getDeposited());
                json.put("expenditure", item.getExpenditure());
                json.put("transactionType", item.getTradeTypeEntity().getTransactionType());
                json.put("currencyNumber", item.getCurrencyNumber());
                json.put("tradeDate", item.getTradeDate());
                json.put("transactionAmount", item.getTransactionAmount());
                json.put("createTime", item.getCreateTime());
                json.put("updateTime", item.getUpdateTime());
                jsonObjectList.add(json);
            }
            OutExcelEntity outExcel = new OutExcelEntity();
            outExcel.setKeys(keys);
            outExcel.setTitles(titles);
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("导出流水异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * 导出完整流水及明细
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<OutExcelEntity> outTransactionInfoExcel(TransactionListEntity entity){
        String[] keys = {"tradeId", "deposited", "expenditure", "transactionType", "currencyNumber", "tradeDate", "transactionAmount", "flog", "itemNumber", "currencyDetails", "createTime", "updateTime"};
        //放置到第一行的字段名
        String[] titles = {"流水号", "存入", "取出", "交易方式", "产生总额", "产生日期", "摘要", "标志", "金额", "详情", "创建时间", "修改时间"};
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = financialDeclareService.selectTransactionFinalCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<TransactionInfoEntity> entityList = financialDeclareService.selectTransactionFinalPage(entity);
            List<JSONObject> jsonObjectList = new ArrayList<>(pageSize.intValue());
            for (TransactionInfoEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("tradeId", item.getTransactionListEntity().getTradeId());
                json.put("deposited", item.getTransactionListEntity().getDeposited());
                json.put("expenditure", item.getTransactionListEntity().getExpenditure());
                json.put("transactionType", item.getTransactionListEntity().getTradeTypeEntity().getTransactionType());
                json.put("currencyNumber", item.getTransactionListEntity().getCurrencyNumber());
                json.put("tradeDate", item.getTransactionListEntity().getTradeDate());
                json.put("transactionAmount", item.getTransactionListEntity().getTransactionAmount());
                if (item.getFlog() == 1) {
                    json.put("flog", "存入");
                } else {
                    json.put("flog", "取出");
                }
                json.put("itemNumber", item.getCurrencyNumber());
                json.put("currencyDetails", item.getCurrencyDetails());
                json.put("createTime", item.getTransactionListEntity().getCreateTime());
                json.put("updateTime", item.getTransactionListEntity().getUpdateTime());
                jsonObjectList.add(json);
            }
            OutExcelEntity outExcel = new OutExcelEntity();
            outExcel.setKeys(keys);
            outExcel.setTitles(titles);
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("导出完整流水及明细异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * 按天分页统计财务报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForDay(TransactionListEntity entity) {
        try {
            Long count = financialDeclareService.selectTransactionForDayCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionForDayPage((TransactionListEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按月统计流水
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForMonth(TransactionListEntity entity) {
        try {
            Long count = financialDeclareService.selectTransactionForMonthCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionForMonthPage((TransactionListEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按年统计流水
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForYear(TransactionListEntity entity){
        try {
            Long count = financialDeclareService.selectTransactionForYearCount(entity);
            if (count > 0) {
                Result<Object> result = PageTools.page(count, entity, (condition) -> financialDeclareService.selectTransactionForYearPage((TransactionListEntity) condition));
                return result;
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<OutExcelEntity> outTransactionForDayExcel(TransactionListEntity entity){
        String[] keys = {"tradeDate", "deposited", "expenditure", "currencyNumber"};
        //放置到第一行的字段名
        String[] titles = {"产生日期", "流入", "流出", "产生总额"};
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = financialDeclareService.selectTransactionForDayCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<TransactionListEntity> entityList = financialDeclareService.selectTransactionForDayPage(entity);
            List<JSONObject> jsonObjectList = new ArrayList<>(pageSize.intValue());
            for (TransactionListEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("tradeDate", item.getTradeDate());
                json.put("deposited", item.getDeposited());
                json.put("expenditure", item.getExpenditure());
                json.put("currencyNumber", item.getCurrencyNumber());
                jsonObjectList.add(json);
            }
            OutExcelEntity outExcel = new OutExcelEntity();
            outExcel.setKeys(keys);
            outExcel.setTitles(titles);
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("按天导出流水统计报表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<OutExcelEntity> outTransactionForMonthExcel(TransactionListEntity entity){
        String[] keys = {"tradeDate", "deposited", "expenditure", "currencyNumber"};
        //放置到第一行的字段名
        String[] titles = {"产生日期", "流入", "流出", "产生总额"};
        OutExcelEntity outExcel = new OutExcelEntity();
        outExcel.setKeys(keys);
        outExcel.setTitles(titles);
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = financialDeclareService.selectTransactionForMonthCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<TransactionListEntity> entityList = financialDeclareService.selectTransactionForMonthPage(entity);
            List<JSONObject> jsonObjectList = new ArrayList<>(pageSize.intValue());
            for (TransactionListEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("tradeDate", item.getTradeDate());
                json.put("deposited", item.getDeposited());
                json.put("expenditure", item.getExpenditure());
                json.put("currencyNumber", item.getCurrencyNumber());
                jsonObjectList.add(json);
            }
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("按月导出流水统计报表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Result<OutExcelEntity> outTransactionForYearExcel(TransactionListEntity entity){
        OutExcelEntity outExcel = new OutExcelEntity();
        String[] keys = {"tradeDate", "deposited", "expenditure", "currencyNumber"};
        outExcel.setKeys(keys);
        //放置到第一行的字段名
        String[] titles = {"产生日期", "流入", "流出", "产生总额"};
        outExcel.setTitles(titles);
        try {
            //获取满足条件的总记录（不分页）
            Long pageSize = financialDeclareService.selectTransactionForYearCount(entity);
            if (pageSize <= 0) {
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            //设置行索引
            entity.setPage(0, pageSize.intValue());
            //获取满足条件的记录集合
            List<TransactionListEntity> entityList = financialDeclareService.selectTransactionForYearPage(entity);
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (TransactionListEntity item : entityList) {
                JSONObject json = new JSONObject();
                json.put("tradeDate", item.getTradeDate());
                json.put("deposited", item.getDeposited());
                json.put("expenditure", item.getExpenditure());
                json.put("currencyNumber", item.getCurrencyNumber());
                jsonObjectList.add(json);
            }
            outExcel.setBodyData(jsonObjectList);
            return ResultUtil.success(outExcel);
        } catch (Exception e) {
            logger.error("按年导出流水统计报表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.ERROP);
        }
    }

}
