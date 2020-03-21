package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.NotesEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.NotesService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.NotesDAO;
import ac.cn.saya.lab.medium.repository.ProceDureDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Title: NotesServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 18:52
 * @Description: 笔记接口实现类
 */
@Service("notesService")
public class NotesServiceImpl implements NotesService {

    private static Logger logger = LoggerFactory.getLogger(NotesServiceImpl.class);

    @Resource
    @Qualifier("notesDAO")
    private NotesDAO notesDAO;

    @Resource
    @Qualifier("proceDureDAO")
    private ProceDureDAO proceDureDAO;

    /**
     * @描述 添加笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> insertNotes(NotesEntity entity) {
        try {
            Integer result = notesDAO.insertNotes(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("创建笔记异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 编辑修改笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> editNotes(NotesEntity entity) {
        try {
            Integer result = notesDAO.updateNotes(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("编辑修改笔记异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deleteNotes(NotesEntity entity) {
        try {
            Integer result = notesDAO.deleteNotes(entity);
            if (result <= 0) {
                return ResultUtil.error(ResultEnum.DB_ERROR);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            logger.error("删除笔记异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一条笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NotesEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<NotesEntity> getOneNotes(NotesEntity entity) {
        try {
            NotesEntity result = notesDAO.getOneNotes(entity);
            if (null != result){
                return ResultUtil.success(result);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("查询笔记异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 获取分页后的笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getNotesPage(NotesEntity entity) {
        try {
            long count = notesDAO.getNotesCount(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> notesDAO.getNotesPage((NotesEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("获取分页后的笔记发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }


    /**
     * @描述 查询指定id附近的笔记
     * @参数  [notesId]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-20
     * @修改人和其它信息
     */
    @Override
    public Result<Map<String,String>> getNotesPreAndNext(Integer notesId) {
        try {
            Map<String, String> map = proceDureDAO.getNewsNotesPreAndNext(2, notesId);
            if (!map.isEmpty()){
                return ResultUtil.success(map);
            }
            return ResultUtil.error(ResultEnum.NOT_EXIST);
        } catch (Exception e) {
            logger.error("获取上下笔记时发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }
}
