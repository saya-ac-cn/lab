package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.NoteBookEntity;
import ac.cn.saya.lab.api.service.medium.NoteBookService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: NoteBookController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:31
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/notebook")
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    /**
     * @描述 添加笔记簿
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insertNoteBook(@RequestBody NoteBookEntity entity){
        return noteBookService.insertNoteBook(entity);
    }

    /**
     * @描述 编辑修改笔记簿
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Object> editNoteBook(@RequestBody NoteBookEntity entity){
        return noteBookService.editNoteBook(entity);
    }

    /**
     * @描述 删除笔记簿
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Object> deleteNoteBook(NoteBookEntity entity){
        return noteBookService.deleteNoteBook(entity);
    }

    /**
     * @描述 查询一条笔记簿
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<Object> getOneNoteBook(NoteBookEntity entity){
        return noteBookService.getOneNoteBook(entity);
    }

    /**
     * @描述 获取分页后的笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getNoteBookPage(NoteBookEntity entity){
        return noteBookService.getNoteBookPage(entity);
    }

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/list")
    public Result<Object> getNoteBook(NoteBookEntity entity){
        return noteBookService.getNoteBook(entity);
    }

}