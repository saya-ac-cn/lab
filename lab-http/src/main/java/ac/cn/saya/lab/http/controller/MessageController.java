package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.http.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: MessageController
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2019/1/11 23:16
 * @Description:
 * 消息服务接口
 */

@RestController
@RequestMapping(value = "/backend/api/message")
public class MessageController {

    @Autowired
    @Qualifier("messageServiceImpl")
    private IMessageService messageServiceImpl;

    /**
     * @描述 发布动态
     * @参数  [entity, request]
     * @返回值  ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PostMapping(value = "news/publish")
    public Result<Object> publishNews(@RequestBody NewsEntity entity, HttpServletRequest request){
        return messageServiceImpl.publishNews(entity,request);
    }

    /**
     * @描述 编辑动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "news/edit")
    public Result<Object> editNews(@RequestBody NewsEntity entity, HttpServletRequest request){
        return messageServiceImpl.editNews(entity,request);
    }

    /**
     * @描述 
     * @参数  删除动态
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "news/delete")
    public Result<Object> deleteNews(NewsEntity entity, HttpServletRequest request){
        return messageServiceImpl.deleteNews(entity,request);
    }

    /**
     * @描述 获取分页的动态
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "news")
    public Result<Object> getNewsList(NewsEntity entity, HttpServletRequest request){
        return  messageServiceImpl.getNewsList(entity,request);
    }

    /**
     * @描述 查询一条动态
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "news/show")
    public Result<NewsEntity> getOneNews(NewsEntity entity, HttpServletRequest request){
        return messageServiceImpl.getOneNews(entity,request);
    }

    /**
     * @描述 审核留言
     * @参数  [entity, request]
     * @返回值  ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @PutMapping(value = "guestbook/check")
    public Result<Object> checkGuestBook(@RequestBody GuestBookEntity entity, HttpServletRequest request){
        return messageServiceImpl.updateGuestBook(entity,request);
    }

    /**
     * @描述 获取分页的留言
     * @参数
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "guestbook")
    public Result<Object> getGuestBookList(GuestBookEntity entity){
        return  messageServiceImpl.getGuestBookList(entity);
    }

    /**
     * @描述 查询一条留言
     * @参数  [entity]
     * @返回值  ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "guestbook/show")
    public Result<GuestBookEntity> getOneNews(GuestBookEntity entity){
        return messageServiceImpl.queryOneGuestBook(entity);
    }

    /**
     * @描述 创建笔记簿
     * @参数  [entity, request]
     * @返回值  ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PostMapping(value = "notebook/create")
    public Result<Object> createNoteBook(@RequestBody NoteBookEntity entity, HttpServletRequest request){
        return messageServiceImpl.createNoteBook(entity,request);
    }

    /**
     * @描述 编辑笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "notebook/edit")
    public Result<Object> editNoteBook(@RequestBody NoteBookEntity entity, HttpServletRequest request){
        return messageServiceImpl.updateNoteBook(entity,request);
    }

    /**
     * @描述
     * @参数  删除笔记簿
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "notebook/delete")
    public Result<Object> deleteNoteBook(NoteBookEntity entity, HttpServletRequest request){
        return messageServiceImpl.deleteNoteBook(entity,request);
    }

    /**
     * @描述 获取分页的笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "notebook")
    public Result<Object> getNoteBookList(NoteBookEntity entity, HttpServletRequest request){
        return  messageServiceImpl.getNoteBookList(entity,request);
    }

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "notebook/group")
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity, HttpServletRequest request){
        return  messageServiceImpl.getNoteBook(entity,request);
    }

    /**
     * @描述 查询一条笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "notebook/show")
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity, HttpServletRequest request){
        return messageServiceImpl.getOneNoteBook(entity,request);
    }

    /**
     * @描述 创建笔记
     * @参数  [entity, request]
     * @返回值  ac.cn.saya.datacenter.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PostMapping(value = "notes/create")
    public Result<Object> createNotes(@RequestBody NotesEntity entity, HttpServletRequest request){
        return messageServiceImpl.createNotes(entity,request);
    }

    /**
     * @描述 编辑笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "notes/edit")
    public Result<Object> editNoteBook(@RequestBody NotesEntity entity, HttpServletRequest request){
        return messageServiceImpl.updateNotes(entity,request);
    }

    /**
     * @描述
     * @参数  删除笔记
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "notes/delete")
    public Result<Object> deleteNotes(NotesEntity entity, HttpServletRequest request){
        return messageServiceImpl.deleteNotes(entity,request);
    }

    /**
     * @描述 获取分页的笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "notes")
    public Result<Object> getNotesList(NotesEntity entity, HttpServletRequest request){
        return  messageServiceImpl.getNotesList(entity,request);
    }

    /**
     * @描述 查询一条笔记
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "notes/show")
    public Result<NotesEntity> getOneNotes(NotesEntity entity, HttpServletRequest request){
        return messageServiceImpl.getOneNotes(entity,request);
    }

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-09-21
     * @修改人和其它信息
     */
    @PostMapping(value = "memo/create")
    public Result<Object> createMemo(@RequestBody MemoEntity entity, HttpServletRequest request){
        return messageServiceImpl.createMemo(entity,request);
    }

    /**
     * @描述 编辑便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @PutMapping(value = "memo/edit")
    public Result<Object> editMemo(@RequestBody MemoEntity entity, HttpServletRequest request){
        return messageServiceImpl.updateMemo(entity,request);
    }

    /**
     * @描述
     * @参数  删除便笺
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    @DeleteMapping(value = "memo/delete")
    public Result<Object> deleteNoteBook(MemoEntity entity, HttpServletRequest request){
        return messageServiceImpl.deleteMemo(entity,request);
    }

    /**
     * @描述 获取分页的便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "memo")
    public Result<Object> getNoteBookList(MemoEntity entity, HttpServletRequest request){
        return  messageServiceImpl.getMemoList(entity,request);
    }

    /**
     * @描述 查询一条便笺
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    @GetMapping(value = "memo/show")
    public Result<MemoEntity> getOneMemo(MemoEntity entity, HttpServletRequest request){
        return messageServiceImpl.getOneMemo(entity,request);
    }

}
