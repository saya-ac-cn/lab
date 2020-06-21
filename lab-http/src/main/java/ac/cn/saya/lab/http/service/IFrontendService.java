package ac.cn.saya.lab.http.service;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title: IFrontendService
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description:
 * 对外提供访问的接口，无需认证
 */

public interface IFrontendService {

    /**
     * @描述 查询一条动态
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<Object> getOneNews(NewsEntity entity);

    /**
     * @描述 获取分页的动态
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getNewsList(NewsEntity entity);

    /**
     * @描述 获取分页文件列表
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-03-20
     * @修改人和其它信息
     */
    public Result<Object> getFileList(FilesEntity entity);

    /**
     * @描述 下载文件
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-03-20
     * @修改人和其它信息
     */
    public Result<Object> downloadFile(String user, Integer id, HttpServletRequest request, HttpServletResponse response);

    /**
     * @描述 留言
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Integer> insertGuestBook(GuestBookEntity entity);

    /**
     * 查看行程安排
     * @描述
     * @参数 [data, request]
     * @返回值 ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/24
     * @修改人和其它信息 查询该月的计划
     */
    public Result<Object> getPlan(String date, String user);

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity);

    /**
     * @描述 获取分页的笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getNotesList(NotesEntity entity);

    /**
     * @描述 查询一条笔记
     * @参数  [entity]
     * @返回值  ac.cn.saya.laboratory.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-04-02
     * @修改人和其它信息
     */
    public Result<Object> getOneNotes(String user, NotesEntity entity);

}
