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
    public Result<Integer> insertNoteBook(NoteBookEntity entity) {
        try {
            Integer result = noteBookDAO.insertNoteBook(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
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
    public Result<Integer> editNoteBook(NoteBookEntity entity) {
        try {
            Integer result = noteBookDAO.updateNoteBook(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
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
    public Result<Integer> deleteNoteBook(NoteBookEntity entity) {
        try {
            Integer result = noteBookDAO.deleteNoteBook(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除笔记簿异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NoteBookEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity) {
        try {
            NoteBookEntity result = noteBookDAO.getOneNoteBook(entity);
            if (null != result){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
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
    public Result<NoteBookEntity> getNoteBook(NoteBookEntity entity) {
        try {
            List<NoteBookEntity> list = noteBookDAO.getNoteBook(entity);
            if (list.size() <= 0) {
                return ResultUtil.error(ResultEnum.NOT_EXIST);
            }
            return ResultUtil.success(list);
        } catch (Exception e) {
            logger.error("获取笔记簿发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @Title 统计笔记簿总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-03
     * @Description
     */
    @Override
    public Result<Long> totalNoteBookCount(NoteBookEntity entity){
        try {
            Long count = noteBookDAO.getNoteBookCount(entity);
            if (count > 0){
                return ResultUtil.success(count);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("统计笔记簿总数时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
