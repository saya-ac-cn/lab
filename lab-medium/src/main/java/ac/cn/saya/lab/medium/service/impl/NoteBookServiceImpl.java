package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.NoteBookEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.NoteBookService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.NoteBookDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: NoteBookServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 18:43
 * @Description: 笔记簿接口实现类
 */
@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService {

    private static Logger logger = LoggerFactory.getLogger(NoteBookServiceImpl.class);

    @Resource
    @Qualifier("noteBookDAO")
    private NoteBookDAO noteBookDAO;

    /**
     * @描述 添加笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> insertNoteBook(NoteBookEntity entity) {
        try {
            return ResultUtil.success(noteBookDAO.insertNoteBook(entity));
        } catch (Exception e) {
            logger.error("创建笔记簿异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 编辑修改笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> editNoteBook(NoteBookEntity entity) {
        try {
            return ResultUtil.success(noteBookDAO.updateNoteBook(entity));
        } catch (Exception e) {
            logger.error("编辑修改笔记簿异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> deleteNoteBook(NoteBookEntity entity) {
        try {
            return ResultUtil.success(noteBookDAO.deleteNoteBook(entity));
        } catch (Exception e) {
            logger.error("删除笔记簿异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.NoteBookEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/1/12
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getOneNoteBook(NoteBookEntity entity) {
        try {
            return ResultUtil.success(noteBookDAO.getOneNoteBook(entity));
        } catch (Exception e) {
            logger.error("查询笔记簿异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNoteBookPage(NoteBookEntity entity) {
        try {
            long count = noteBookDAO.getNoteBookCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> noteBookDAO.getNoteBookPage((NoteBookEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的笔记簿发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNoteBook(NoteBookEntity entity) {
        try {
            List<NoteBookEntity> list = noteBookDAO.getNoteBook(entity);
            if (list.size() <= 0) {
                list = null;
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("获取笔记簿发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}