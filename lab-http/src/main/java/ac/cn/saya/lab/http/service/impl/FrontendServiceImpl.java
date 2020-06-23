package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.DateUtils;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.*;
import ac.cn.saya.lab.http.service.IFrontendService;
import ac.cn.saya.lab.http.tools.UploadUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: FrontendServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/21 10:35
 * @Description: 对外提供访问的接口
 */
@Service
public class FrontendServiceImpl implements IFrontendService {

    @Resource
    private NewsFeignClient newsFeignClient;

    @Resource
    private FilesFeignClient filesFeignClient;

    @Resource
    private GuestBookFeignClient guestBookFeignClient;

    @Resource
    private PlanFeignClient planFeignClient;

    @Resource
    private NoteBookFeignClient noteBookFeignClient;

    @Resource
    private NotesFeignClient notesFeignClient;

    /**
     * @描述 查询一条动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOneNews(NewsEntity entity) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<NewsEntity> result = newsFeignClient.getOneNews(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            // 寻找上一条和下一条
            Result<Map<String, String>> resultMap = newsFeignClient.getNewsPreAndNext(entity.getId());
            Map<String, NewsEntity> out = new HashMap(4);
            out.put("now", result.getData());
            if (ResultUtil.checkSuccess(resultMap)) {
                Map<String, String> preAndNext = resultMap.getData();
                for (Map.Entry<String, String> item : preAndNext.entrySet()) {
                    entity.setId(Integer.valueOf(item.getValue()));
                    result = newsFeignClient.getOneNews(entity);
                    if (ResultUtil.checkSuccess(result)) {
                        out.put(item.getKey(), result.getData());
                    }
                }
            }
            return ResultUtil.success(out);
        }
    }

    /**
     * @描述 获取分页的动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNewsList(NewsEntity entity) {
        return newsFeignClient.getNewsPage(entity);
    }

    /**
     * @描述 获取分页文件列表
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-20
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getFileList(FilesEntity entity) {
        return filesFeignClient.getFilePage(entity);
    }

    /**
     * @描述 下载文件
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-03-20
     * @修改人和其它信息
     */
    @Override
    public Result<Object> downloadFile(String user, Integer id, HttpServletRequest request, HttpServletResponse response)  {
        if (id == null) {
            // 缺少参数
            response.setStatus(400);
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        FilesEntity queryEntity = new FilesEntity();
        queryEntity.setId(id);
        queryEntity.setSource(user);
        queryEntity.setStatus("1");
        Result<FilesEntity> result = filesFeignClient.getOneFile(queryEntity);
        if (!ResultUtil.checkSuccess(result) || StringUtils.isEmpty(result.getData().getFileurl())) {
            response.setStatus(404);
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            FilesEntity resultEntity = result.getData();
            File thisFile = UploadUtils.getFilePath(resultEntity.getFileurl());
            if (thisFile == null) {
                // 文件不存在
                response.setStatus(404);
                throw new MyException(ResultEnum.NOT_EXIST);
            }
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            //且仅当此对象抽象路径名表示的文件或目录存在时，返回true
            response.setContentType("application/x-download");
            //控制下载文件的名字
            response.addHeader("Content-Disposition", "attachment;filename=" + resultEntity.getFilename());
            byte buf[] = new byte[1024];
            try {
                fis = new FileInputStream(thisFile);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buf);
                while (i != -1) {
                    os.write(buf, 0, i);
                    i = bis.read(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ResultUtil.success();
        }
    }

    /**
     * @描述 留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertGuestBook(GuestBookEntity entity) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        // 设置为待审核
        entity.setStatus(2);
        return guestBookFeignClient.insertGuestBook(entity);
    }

    /**
     * 查看行程安排
     *
     * @描述
     * @参数 [data, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询该月的计划
     */
    @Override
    public Result<Object> getPlan(String date, String user){
        // 第一天
        String firstDay = DateUtils.getFirstDayOfMonth(date);
        // 最后一天
        String lastDay = DateUtils.getLastDayOfMonth(date);
        // 获取该月的总天数
        int monthCount = DateUtils.getLengthOfMonth(date);
        // 获取该月第一天是星期几--星期日i==1，星期六i==7
        int firstDayWeek = DateUtils.getFirstDayWeek(date);
        PlanEntity query = new PlanEntity();
        // 查询该用户的计划
        query.setSource(user);
        // 查询该月的计划
        query.setBeginTime(firstDay);
        query.setEndTime(lastDay);
        Result<List<PlanEntity>> planResult = planFeignClient.getPlanList(query);
        List<PlanEntity> plan = null;
        if (ResultUtil.checkSuccess(planResult)) {
            plan = planResult.getData();
        }
        if (firstDayWeek < 1 || firstDayWeek > 7) {
            // 获取的该月第一天是否正常
            throw new MyException(ResultEnum.ERROP);
        } else {
            // 表格中单元格个数（1号前的空单元格加上日历的单元格）
            Integer gridCount = monthCount + (firstDayWeek - 1);
            // 总行数
            Integer tableLine = 5;
            if (gridCount % 7 == 0) {
                tableLine = gridCount / 7;
            } else {
                tableLine = gridCount / 7 + 1;
            }
            // 统计有效的单元格（加上月尾的空白单元格）
            gridCount = tableLine * 7;
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (int i = 1; i <= gridCount; i++) {
                JSONObject json = new JSONObject();
                if (i >= firstDayWeek && i <= (monthCount + (firstDayWeek - 1))) {
                    json.put("flog", 1);
                    json.put("number", i - (firstDayWeek - 1));
                    json.put("value", 0);
                    json.put("id", -1);
                    // 输出日历
                    if (plan != null) {
                        for (PlanEntity item : plan) {
                            if (item.getNumber() == (i - (firstDayWeek - 1))) {
                                // 该天有内容
                                json.put("value", item.getDescribe());
                                json.put("id", item.getId());
                                break;
                            }
                        }
                    }
                } else {
                    // 输出空白
                    json.put("flog", 0);
                    json.put("number", 0);
                    json.put("value", 0);
                }
                jsonObjectList.add(json);
            }
            return ResultUtil.success(jsonObjectList);
        }
    }

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity) {
        entity.setStatus(1);
        return noteBookFeignClient.getNoteBook(entity);
    }

    /**
     * @描述 获取分页的笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNotesList(NotesEntity entity){
        return notesFeignClient.getNotesPage(entity);
    }

    /**
     * @描述 查询一条笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.laboratory.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019-04-02
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOneNotes(String user, NotesEntity entity){
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        // 设置查询的用户为当前的用户
        NoteBookEntity bookEntity = entity.getNotebook();
        if (bookEntity == null) {
            bookEntity = new NoteBookEntity();
        }
        bookEntity.setSource(user);
        entity.setNotebook(bookEntity);
        Result<NotesEntity> result = notesFeignClient.getOneNotes(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            // 寻找上一条和下一条
            Result<Map<String, String>> resultMap = notesFeignClient.getNotesPreAndNext(entity.getId());
            Map<String, NotesEntity> out = new HashMap(4);
            out.put("now", result.getData());
            if (ResultUtil.checkSuccess(resultMap)) {
                Map<String, String> preAndNext = resultMap.getData();
                for (Map.Entry<String, String> item : preAndNext.entrySet()) {
                    entity.setId(Integer.valueOf(item.getValue()));
                    result = notesFeignClient.getOneNotes(entity);
                    if (ResultUtil.checkSuccess(result)) {
                        out.put(item.getKey(), result.getData());
                    }
                }
            }
            return ResultUtil.success(out);
        }
    }
}
