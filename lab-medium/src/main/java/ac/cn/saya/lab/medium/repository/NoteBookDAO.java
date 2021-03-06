package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.NoteBookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: NoteBookDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author saya.ac.cn-刘能凯
 * @Date: 2020-02-29
 * @Description:
 * 动态DAO
 */

@Mapper
public interface NoteBookDAO {

    /**
     * @描述 新增笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer insertNoteBook(NoteBookEntity entity);

    /**
     * @描述 编辑笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer updateNoteBook(NoteBookEntity entity);

    /**
     * @描述  删除笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer deleteNoteBook(NoteBookEntity entity);

    /**
     * @描述 查询一条笔记簿
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public NoteBookEntity getOneNoteBook(NoteBookEntity entity);

    /**
     * @描述 获取分页后的笔记簿
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public List<NoteBookEntity> getNoteBookPage(NoteBookEntity entity);

    /**
     * @描述 获取笔记簿总数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Long getNoteBookCount(NoteBookEntity entity);

    /**
     * @描述 获取笔记簿不分页
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/20
     * @修改人和其它信息
     */
    public List<NoteBookEntity> getNoteBook(NoteBookEntity entity);

}
