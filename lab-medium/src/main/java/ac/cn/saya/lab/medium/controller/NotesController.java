package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.NotesEntity;
import ac.cn.saya.lab.api.service.medium.NotesService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: NotesController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:37
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    /**
     * @描述 添加笔记
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insertNotes(@RequestBody NotesEntity entity){
        return notesService.insertNotes(entity);
    }

    /**
     * @描述 编辑修改笔记
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Object> editNotes(@RequestBody NotesEntity entity){
        return notesService.editNotes(entity);
    }

    /**
     * @描述 删除笔记
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Object> deleteNotes(NotesEntity entity){
        return notesService.deleteNotes(entity);
    }

    /**
     * @描述 查询一条笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.datacenter.entity.NotesEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<Object> getOneNotes(NotesEntity entity){
        return notesService.getOneNotes(entity);
    }

    /**
     * @描述 获取分页后的笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getNotesPage(NotesEntity entity){
        return notesService.getNotesPage(entity);
    }

    /**
     * @描述 查询指定id附近的笔记
     * @参数  [notesId]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/nearby")
    public Result<Object> getNotesPreAndNext(@RequestParam(value = "notesId") Integer notesId){
        return notesService.getNotesPreAndNext(notesId);
    }

}
