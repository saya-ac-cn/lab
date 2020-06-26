package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.TransactionReadFeignClient;
import ac.cn.saya.lab.http.feignclient.TransactionWriteFeignClient;
import ac.cn.saya.lab.http.service.IFinancialService;
import ac.cn.saya.lab.http.tools.OutExcelUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Title: FinancialServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/21 09:20
 * @Description: 财政数据提供的相关服务
 */
@Service
public class FinancialServiceImpl implements IFinancialService {

    @Resource
    private RecordService recordService;

    @Resource
    private TransactionReadFeignClient transactionReadFeignClient;

    @Resource
    private TransactionWriteFeignClient transactionWriteFeignClient;

    /**
     * 获取所有的交易类别
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<List<TransactionTypeEntity>> getTransactionType() {
        return transactionReadFeignClient.getTransactionType();
    }

    /**
     * 查看流水（这里不是明细）
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransaction(TransactionListEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return transactionReadFeignClient.getTransactionPage(entity);
    }

    /**
     * 查看流水子明细
     * 根据父id，本位id，flog
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionInfo(TransactionInfoEntity entity, HttpServletRequest request){
        return transactionReadFeignClient.getTransactionInfoPage(entity);
    }

    /**
     * 查询详细的流水明细
     * 根据用户、类型、日期
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> getTransactionFinal(TransactionListEntity entity, HttpServletRequest request){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return transactionReadFeignClient.getTransactionFinalPage(entity);
    }

    /**
     * 添加财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransaction(TransactionListEntity entity, HttpServletRequest request){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Object> result = transactionWriteFeignClient.insertTransactionList(entity);
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            recordService.record("OX025", request);
        }
        return result;
    }

    /**
     * 修改财政记录父
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransaction(TransactionListEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Object> result = transactionWriteFeignClient.updateTransactionList(entity);
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            recordService.record("OX026", request);
        }
        return result;
    }

    /**
     * 删除财政记录父+子
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransaction(TransactionListEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Object> result = transactionWriteFeignClient.deleteTransactionList(entity);
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            /**
             * 删除流水
             */
            recordService.record("OX027", request);
        }
        return result;
    }

    /**
     * 添加财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> insertTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        Result<Object> result = transactionWriteFeignClient.insertTransactionInfo(entity, userSession.getUser());
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            /**
             * 删除流水
             */
            recordService.record("OX028", request);
        }
        return result;
    }

    /**
     * 修改财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> updateTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        Result<Object> result = transactionWriteFeignClient.updateTransactionInfo(entity, userSession.getUser());
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            recordService.record("OX029", request);
        }
        return result;
    }

    /**
     * 删除财政子记录
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> deleteTransactioninfo(TransactionInfoEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        Result<Object> result = transactionWriteFeignClient.deleteTransactionInfo(entity, userSession.getUser());
        if (result.getCode() == ResultEnum.SUCCESS.getCode()) {
            recordService.record("OX030", request);
        }
        return result;
    }

    /**
     * 导出流水
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionListExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        try {
            Result<OutExcelEntity> excel = transactionReadFeignClient.outTransactionListExcel(entity);
            if (!ResultUtil.checkSuccess(excel)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("财务流水.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(excel.getData().getKeys(), excel.getData().getTitles(), excel.getData().getBodyData(), out);
            return ResultUtil.success();
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROP);
        }
    }

    /**
     * 导出完整流水及明细
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionInfoExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        try {
            Result<OutExcelEntity> excel = transactionReadFeignClient.outTransactionInfoExcel(entity);
            if (!ResultUtil.checkSuccess(excel)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("财务流水明细.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(excel.getData().getKeys(), excel.getData().getTitles(), excel.getData().getBodyData(), out);
            return ResultUtil.success();
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROP);
        }
    }

    /**
     * 按天统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForDay(TransactionListEntity entity, HttpServletRequest request) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return transactionReadFeignClient.getTransactionForDayPage(entity);
    }

    /**
     * 按月统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForMonth(TransactionListEntity entity, HttpServletRequest request){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return transactionReadFeignClient.getTransactionForMonthPage(entity);
    }

    /**
     * 按年统计流水
     *
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> totalTransactionForYear(TransactionListEntity entity, HttpServletRequest request){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return transactionReadFeignClient.getTransactionForYearPage(entity);
    }

    /**
     * 按天导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForDayExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        try {
            Result<OutExcelEntity> excel = transactionReadFeignClient.outTransactionForDayExcel(entity);
            if (!ResultUtil.checkSuccess(excel)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("日度财务明细.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(excel.getData().getKeys(), excel.getData().getTitles(), excel.getData().getBodyData(), out);
            return ResultUtil.success();
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROP);
        }
    }

    /**
     * 按月导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForMonthExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response) {
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        try {
            Result<OutExcelEntity> excel = transactionReadFeignClient.outTransactionForMonthExcel(entity);
            if (!ResultUtil.checkSuccess(excel)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("月度财务明细.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(excel.getData().getKeys(), excel.getData().getTitles(), excel.getData().getBodyData(), out);
            return ResultUtil.success();
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROP);
        }
    }

    /**
     * 按年导出流水统计报表
     *
     * @param entity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> outTransactionForYearExcel(TransactionListEntity entity, HttpServletRequest request, HttpServletResponse response){
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        try {
            Result<OutExcelEntity> excel = transactionReadFeignClient.outTransactionForYearExcel(entity);
            if (!ResultUtil.checkSuccess(excel)){
                response.setStatus(404);
                return ResultUtil.error(-1, "没有可导出的数据");
            }
            // 设置contentType为excel格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("年度财务明细.xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            response.flushBuffer();
            OutExcelUtils.outExcelTemplateSimple(excel.getData().getKeys(), excel.getData().getTitles(), excel.getData().getBodyData(), out);
            return ResultUtil.success();
        } catch (Exception e) {
            response.setStatus(500);
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERROP);
        }
    }
}
