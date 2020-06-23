package ac.cn.saya.lab.http.service.impl;

import ac.cn.saya.lab.api.entity.*;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.*;
import ac.cn.saya.lab.http.service.IMessageService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: MessageServiceImpl
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/23 20:57
 * @Description:
 */
@Service
public class MessageServiceImpl implements IMessageService {

    private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Resource
    @Qualifier("recordService")//日志助手表
    private RecordService recordService;

    @Resource
    private NewsFeignClient newsFeignClient;

    @Resource
    private GuestBookFeignClient guestBookFeignClient;

    @Resource
    private NoteBookFeignClient noteBookFeignClient;

    @Resource
    private NotesFeignClient notesFeignClient;

    @Resource
    private MemoFeignClient memoFeignClient;

    /**
     * @描述 发布动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> publishNews(NewsEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = newsFeignClient.publishNews(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX007", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 编辑动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> editNews(NewsEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = newsFeignClient.editNews(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX008", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteNews(NewsEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = newsFeignClient.deleteNews(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX009", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 查询一条动态
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<NewsEntity> getOneNews(NewsEntity entity, HttpServletRequest request) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<NewsEntity> result = newsFeignClient.getOneNews(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
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
    public Result<Object> getNewsList(NewsEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return newsFeignClient.getNewsPage(entity);
    }

    /**
     * @描述 审核修改
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateGuestBook(GuestBookEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = guestBookFeignClient.updateGuestBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX010", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 查询一条留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<GuestBookEntity> queryOneGuestBook(GuestBookEntity entity) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<GuestBookEntity> result = guestBookFeignClient.queryOneGuestBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * @描述 获取分页的留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getGuestBookList(GuestBookEntity entity) {
        return guestBookFeignClient.getGuestBookPage(entity);
    }

    /**
     * @描述 创建笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> createNoteBook(NoteBookEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = noteBookFeignClient.insertNoteBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX016", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateNoteBook(NoteBookEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = noteBookFeignClient.editNoteBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX017", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteNoteBook(NoteBookEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = noteBookFeignClient.deleteNoteBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX018", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 查询一条笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity, HttpServletRequest request) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<NoteBookEntity> result = noteBookFeignClient.getOneNoteBook(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * @描述 获取分页的笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNoteBookList(NoteBookEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return noteBookFeignClient.getNoteBookPage(entity);
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
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<List<NoteBookEntity>> result = noteBookFeignClient.getNoteBook(entity);
        if (ResultUtil.checkSuccess(result)) {
            return result;
        } else {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        }
    }

    /**
     * @描述 创建笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> createNotes(NotesEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = notesFeignClient.insertNotes(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX019", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateNotes(NotesEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        NoteBookEntity bookEntity = new NoteBookEntity();
        bookEntity.setSource(userSession.getUser());
        entity.setNotebook(bookEntity);
        Result<Integer> result = notesFeignClient.editNotes(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX020", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteNotes(NotesEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<Integer> result = notesFeignClient.deleteNotes(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX021", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 查询一条笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<NotesEntity> getOneNotes(NotesEntity entity, HttpServletRequest request) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        NoteBookEntity bookEntity = entity.getNotebook();
        if (bookEntity == null) {
            bookEntity = new NoteBookEntity();
        }
        bookEntity.setSource(userSession.getUser());
        entity.setNotebook(bookEntity);
        Result<NotesEntity> result = notesFeignClient.getOneNotes(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
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
    public Result<Object> getNotesList(NotesEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        NoteBookEntity bookEntity = entity.getNotebook();
        if (bookEntity == null) {
            bookEntity = new NoteBookEntity();
        }
        bookEntity.setSource(userSession.getUser());
        entity.setNotebook(bookEntity);
        return notesFeignClient.getNotesPage(entity);
    }

    /**
     * @描述 创建便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> createMemo(MemoEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null || StringUtils.isEmpty(entity.getTitle()) || StringUtils.isEmpty(entity.getContent())) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = memoFeignClient.insert(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX034", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 修改便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> updateMemo(MemoEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (entity == null || null == entity.getId() || StringUtils.isEmpty(entity.getTitle()) || StringUtils.isEmpty(entity.getContent())) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = memoFeignClient.update(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX035", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 删除便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteMemo(MemoEntity entity, HttpServletRequest request) {
        // 校验用户输入的参数
        if (null == entity || null == entity.getId()) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        //在session中取出管理员的信息   最后放入的都是 用户名 不是邮箱
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        Result<Integer> result = memoFeignClient.delete(entity);
        if (ResultUtil.checkSuccess(result)) {
            /**
             * 记录日志
             */
            recordService.record("OX035", request);
            return ResultUtil.success();
        } else {
            throw new MyException(ResultEnum.ERROP);
        }
    }

    /**
     * @描述 查询一条便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<MemoEntity> getOneMemo(MemoEntity entity, HttpServletRequest request) {
        if (entity == null || entity.getId() == null) {
            // 缺少参数
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        Result<MemoEntity> result = memoFeignClient.getOne(entity);
        if (!ResultUtil.checkSuccess(result)) {
            //未找到有效记录
            throw new MyException(ResultEnum.NOT_EXIST);
        } else {
            return result;
        }
    }

    /**
     * @描述 获取分页的便笺
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/11
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getMemoList(MemoEntity entity, HttpServletRequest request) {
        UserMemory userSession = (UserMemory) request.getSession().getAttribute("user");
        entity.setSource(userSession.getUser());
        return memoFeignClient.getPage(entity);
    }
}
