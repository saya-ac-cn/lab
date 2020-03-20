package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.NotesEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: NotesFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:37
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "notes")
public interface NotesFeignClient {


    /**
     * @描述 添加笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/notes")
    public Result<Integer> insertNotes(@RequestBody NotesEntity entity);

    /**
     * @描述 编辑修改笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/medium/notes")
    public Result<Integer> editNotes(@RequestBody NotesEntity entity);

    /**
     * @描述 删除笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/notes")
    public Result<Integer> deleteNotes(NotesEntity entity);

    /**
     * @描述 查询一条笔记
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.NotesEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/notes/one")
    public Result<NotesEntity> getOneNotes(NotesEntity entity);

    /**
     * @描述 获取分页后的笔记
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/notes/pagin")
    public Result<Object> getNotesPage(NotesEntity entity);

    /**
     * @描述  查询指定id附近的笔记
     * @参数  [notesId]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-20
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/notes/nearby")
    public Result<Map<String,String>> getNotesPreAndNext(@RequestParam(value = "notesId") Integer notesId);

}
