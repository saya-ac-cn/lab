package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.NoteBookEntity;
import ac.cn.saya.lab.api.tools.Result;

/**
 * @Title: NoteBookService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:27
 * @Description:
 */

public interface NoteBookService {

    /**
     * @描述 添加笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> insertNoteBook(NoteBookEntity entity);

    /**
     * @描述 编辑修改笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> editNoteBook(NoteBookEntity entity);

    /**
     * @描述 删除笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> deleteNoteBook(NoteBookEntity entity);

    /**
     * @描述 查询一条笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NoteBookEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity);

    /**
     * @描述 获取分页后的笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Object> getNoteBookPage(NoteBookEntity entity);

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<NoteBookEntity> getNoteBook(NoteBookEntity entity);

}
