package ac.cn.saya.lab.http.service;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: IMessageService
 * @ProjectName lab
 * @Description: TODO
 * @Author Saya
 * @Date: 2020-02-29 18:42:39
 * @Description:消息接口
 */

public interface IMessageService {

    /**
     * @描述 发布动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> publishNews(NewsEntity entity, HttpServletRequest request);

    /**
     * @描述 编辑动态
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> editNews(NewsEntity entity, HttpServletRequest request);

    /**
     * @描述 删除动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> deleteNews(NewsEntity entity, HttpServletRequest request);

    /**
     * @描述 查询一条动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<NewsEntity> getOneNews(NewsEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页的动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getNewsList(NewsEntity entity, HttpServletRequest request);


    /**
     * @描述 审核修改
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> updateGuestBook(GuestBookEntity entity, HttpServletRequest request);

    /**
     * @描述 查询一条留言
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<GuestBookEntity> queryOneGuestBook(GuestBookEntity entity);

    /**
     * @描述 获取分页的留言
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getGuestBookList(GuestBookEntity entity);

    /**
     * @描述 创建笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> createNoteBook(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 修改笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> updateNoteBook(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 删除笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> deleteNoteBook(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 查询一条笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页的笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getNoteBookList(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity, HttpServletRequest request);

    /**
     * @描述 创建笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> createNotes(NotesEntity entity, HttpServletRequest request);

    /**
     * @描述 修改笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> updateNotes(NotesEntity entity, HttpServletRequest request);

    /**
     * @描述 删除笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> deleteNotes(NotesEntity entity, HttpServletRequest request);

    /**
     * @描述 查询一条笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<NotesEntity> getOneNotes(NotesEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页的笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getNotesList(NotesEntity entity, HttpServletRequest request);

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> createMemo(MemoEntity entity, HttpServletRequest request);

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> updateMemo(MemoEntity entity, HttpServletRequest request);

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> deleteMemo(MemoEntity entity, HttpServletRequest request);

    /**
     * @描述 查询一条便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<MemoEntity> getOneMemo(MemoEntity entity, HttpServletRequest request);

    /**
     * @描述 获取分页的便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Result<Object> getMemoList(MemoEntity entity, HttpServletRequest request);

}
