package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.NotesEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

/**
 * @Title: NotesService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:35
 * @Description:
 */

public interface NotesService {

    /**
     * @描述 添加笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> insertNotes(NotesEntity entity);

    /**
     * @描述 编辑修改笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> editNotes(NotesEntity entity);

    /**
     * @描述 删除笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> deleteNotes(NotesEntity entity);

    /**
     * @描述 查询一条笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NotesEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<NotesEntity> getOneNotes(NotesEntity entity);

    /**
     * @描述 获取分页后的笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Object> getNotesPage(NotesEntity entity);

    /**
     * @描述 查询指定id附近的笔记
     * @参数 [notesId]
     * @返回值 java.util.Map<java.lang.String               ,               java.lang.String>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Map<String,String>> getNotesPreAndNext(Integer notesId);


    /**
     * @Title 统计笔记总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-03
     * @Description
     */
    public Result<Long> totalNotesCount(NotesEntity entity);

}
