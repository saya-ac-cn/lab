package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.BackupLogEntity;
import ac.cn.saya.lab.api.entity.PlanEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.http.feignclient.BackFeignClient;
import ac.cn.saya.lab.http.feignclient.PlanFeignClient;
import ac.cn.saya.lab.http.service.SystemService;
import ac.cn.saya.lab.http.thread.MysqlDumpThread;
import ac.cn.saya.lab.http.tools.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: SystemServiceImpl
 * @ProjectName laboratory
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-03-01 20:27
 * @Description:
 */

@Service("systemServiceImpl")
public class SystemServiceImpl implements SystemService {

    private static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Value("${backup.dburl}")
    private String dburl;

    @Value("${backup.dbname}")
    private String dbname;

    @Value("${backup.mysqlbin}")
    private String mysqlbin;

    @Value("${backup.mail}")
    private String mail;

    @Value("${backup.savemonth}")
    private Integer savemonth;

    @Value("${backup.username}")
    private String username;

    @Value("${backup.password}")
    private String password;

    @Resource
    private BackFeignClient backFeignClient;

    @Resource
    private PlanFeignClient planFeignClient;

    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    /**
     * @描述 备份数据库、每天3点备份一次
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-01
     * @修改人和其它信息
     */
    @Override
    // 每天3点执行
    @Scheduled(cron = "${backup.scheduled}")
    // 每隔5分钟执行一次
    //@Scheduled(cron = "0 */1 * * * ?")
    public Boolean backupDatabase() {
        try {
            String executeTime =  DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat);
            //url路径 files/database
            String urlPath = File.separator + "warehouse" + File.separator + "database";
            //上传文件路径-/database/目录下当天的文件夹
            String backUppath = System.getProperty("user.home","/home/saya") + urlPath;
            //保存的文件名
            String backUpName = RandomUtil.getRandomFileName() + ".sql";
            MysqlDumpThread task = new MysqlDumpThread(this.dburl, this.username, this.password, backUppath, backUpName, this.dbname, this.mysqlbin);
            FutureTask<Boolean> futureTask = new FutureTask(task);
            futureTask.run();
            // 保存本次执行任务，必须要在备份前保存本次任务，否则将出现异常
            Result<Integer> insretResult = backFeignClient.insert(urlPath + File.separator + backUpName);
            if (ResultUtil.checkSuccess(insretResult)){
                boolean result = futureTask.get();
                if (result) {
                    // 记录此次的备份情况
                    sendBackUpMail(executeTime, "成功", (urlPath + File.separator + backUpName));
                } else {
                    logger.error("数据库备份失败");
                    sendBackUpMail(executeTime, "失败", "-");
                }
            }else {
                logger.error("数据库备份失败");
            }
        } catch (ExecutionException e){
            logger.error("备份数据库计划异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }catch (InterruptedException e) {
            logger.error("备份数据库计划异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
        return false;
    }

    /**
     * @描述 删除数据库备份 每月1日凌晨1点执行
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-02
     * @修改人和其它信息
     */
    @Override
    @Scheduled(cron = "0 0 1 1 * ?")
    public Boolean deleteBackup() {
        //查询一个月以前的数据库备份总数
        try {
            BackupLogEntity queryEntity = new BackupLogEntity();
            queryEntity.setSaveMonth(savemonth);
            Result<List<BackupLogEntity>> result = backFeignClient.getBackupList(queryEntity);
            List<BackupLogEntity> list = null;
            if (ResultUtil.checkSuccess(result) && !(list=result.getData()).isEmpty()) {
                // 执行删除计划
                // 删除磁盘文件
                for (BackupLogEntity item : list) {
                    UploadUtils.deleteFile(item.getUrl());
                }
                // 删除数据库记录
                Result<Integer> deleteResult = backFeignClient.deleteBackup(queryEntity);
                if (ResultUtil.checkSuccess(deleteResult)){
                    return true;
                }else {
                    throw new MyException(ResultEnum.ERROP);
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("清理备份数据库计划异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            return false;
        }
    }

    /**
     * @描述 计划安排邮件发送提醒（每天4点执行）
     * @参数 []
     * @返回值 java.lang.Boolean
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-06-03
     * @修改人和其它信息
     */
    @Override
    // 每天4点执行
    @Scheduled(cron = "0 0 4 * * ?")
    public Boolean remindPlan() {
        Result<List<PlanEntity>> result = planFeignClient.getTodayPlanList();
        List<PlanEntity> list = null;
        try {
            if (!ResultUtil.checkSuccess(result) || (list = result.getData()).isEmpty()) {
                // 今日无计划安排
                return false;
            } else {
                // 今日有计划安排
                for (PlanEntity item : list) {
                    sendRemndPlanMail(item.getUpdatetime(), item.getSource(), item.getCreatetime(), item.getDescribe());
                }
                return true;
            }
        } catch (Exception e) {
            logger.error("日程安排邮件发送定时任务异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
        return false;
    }

    /**
     * @描述 发送数据库备份结果报告邮件
     * @参数 executeTime 执行时间
     * @参数 executeResult 执行结果
     * @参数 saveUrl 保存位置
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-01
     * @修改人和其它信息
     */
    public void sendBackUpMail(String executeTime, String executeResult, String saveUrl) {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("executeTime", executeTime);
        context.setVariable("executeResult", executeResult);
        context.setVariable("saveUrl", saveUrl);
        context.setVariable("sendTime", DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat));
        try {
            String emailContent = templateEngine.process("mail/backUpDB", context);
            mailService.sendHtmlMail(mail, "数据库备份结果报告", emailContent);
        } catch (Exception e) {
            logger.error("邮件发送异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
    }

    /**
     * @描述 发送计划安排邮件提醒
     * @参数 [userEmail, userName, createTime, planContent]
     * @返回值 void
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-06-03
     * @修改人和其它信息
     */
    public void sendRemndPlanMail(String userEmail, String userName, String createTime, String planContent) {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("createTime", createTime);
        context.setVariable("planContent", planContent);
        context.setVariable("sendTime", DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat));
        try {
            String emailContent = templateEngine.process("mail/remindPlan", context);
            mailService.sendHtmlMail(userEmail, "今日计划安排提醒", emailContent);
        } catch (Exception e) {
            logger.error("邮件发送异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
    }

    /**
     * @Title
     * @Params  [userEmail, userName, account, ip, city, platform, loginTime]
     * @Return  void
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-06-06
     * @Description
     */
    public void sendLoginNotice(String userEmail,String userName,String account,String ip,String city,String platform,String loginTime){
        //创建邮件正文
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("account", account);
        context.setVariable("platform", (platform.equals("lab")?"实验室中心后台":"财政申报系统"));
        context.setVariable("ip", ip);
        context.setVariable("city", city);
        context.setVariable("ip", ip);
        context.setVariable("loginTime", loginTime);
        context.setVariable("sendTime", DateUtils.getCurrentDateTime(DateUtils.dateTimeFormat));
        try {
            String emailContent = templateEngine.process("mail/loginNotice", context);
            mailService.sendHtmlMail(userEmail, "登录提醒", emailContent);
        } catch (Exception e) {
            logger.error("邮件发送异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
        }
    }

}
