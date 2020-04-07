package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.NoteBookEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: NoteBookFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:31
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "notebook",path = "/medium/notebook")
public interface NoteBookFeignClient {


    /**
     * @描述 添加笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insertNoteBook(@RequestBody NoteBookEntity entity);

    /**
     * @描述 编辑修改笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> editNoteBook(@RequestBody NoteBookEntity entity);

    /**
     * @描述 删除笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deleteNoteBook(NoteBookEntity entity);

    /**
     * @描述 查询一条笔记簿
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.NoteBookEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<NoteBookEntity> getOneNoteBook(NoteBookEntity entity);

    /**
     * @描述 获取分页后的笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getNoteBookPage(NoteBookEntity entity);

    /**
     * @描述 获取笔记簿
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @GetMapping(value = "/list")
    public Result<List<NoteBookEntity>> getNoteBook(NoteBookEntity entity);

    /**
     * @Title 统计笔记簿总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-03
     * @Description
     */
    @GetMapping(value = "/totalNoteBookCount")
    public Result<Long> totalNoteBookCount(NoteBookEntity entity);

}
