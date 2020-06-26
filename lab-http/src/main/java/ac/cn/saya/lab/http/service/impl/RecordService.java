package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.LogEntity;
import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.api.tools.DateUtils;
import ac.cn.saya.lab.api.tools.Log4jUtils;
import ac.cn.saya.lab.http.feignclient.LogFeignClient;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: RecordService
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/9/22 22:40
 * @Description: 日志记录专用类
 */

@Service("recordService")
public class RecordService {

    private static Logger logger = LoggerFactory.getLogger(RecordService.class);

    @Resource
    @Qualifier("logFeignClient")
    private LogFeignClient logFeignClient;


    public RecordService() {
    }

    /**
     * 日志记录
     *
     * @param type
     * @param httpRequest
     */
    public void record(String type, HttpServletRequest httpRequest) {
        try {
            //在session中取出管理员的名字
//            UserMemory user = HttpRequestUtil.getUserMemory(httpRequest);
//            String datetime = DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat);
//            LogEntity entity = new LogEntity(user.getUser(), type, user.getIp(), user.getCity(), datetime);
//            logFeignClient.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("记录日志异常" + Log4jUtils.getTrace(e));
        }
    }

    /**
     * 日志记录
     *
     * @param type
     * @param memory
     */
    public void record(String type, UserMemory memory) {
        try {
            String datetime = DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat);
            LogEntity entity = new LogEntity(memory.getUser(), type, memory.getIp(), memory.getCity(), datetime);
            logFeignClient.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("记录日志异常" + Log4jUtils.getTrace(e));
        }
    }

}