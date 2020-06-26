package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.service.medium.MemoService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title: MemoController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:01
 * @Description:
 */
@RestController
@RequestMapping(value = "medium/memo")
public class MemoController {
    @Autowired
    private MemoService memoService;

    /**
     * @描述 创建便笺
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insert(@RequestBody MemoEntity entity){
        return memoService.insert(entity);
    }

    /**
     * @描述 查询便笺
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.MemoEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<MemoEntity> getOne(@RequestBody MemoEntity entity){
        return memoService.getOne(entity);
    }

    /**
     * @描述 修改便笺
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> update(@RequestBody MemoEntity entity){
        return memoService.update(entity);
    }

    /**
     * @描述 删除便笺
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> delete(@RequestBody MemoEntity entity){
        return memoService.delete(entity);
    }

    /**
     * @描述 获取分页后的便笺
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getPage(@RequestBody MemoEntity entity){
        return memoService.getPage(entity);
    }

    /**
     * @Title 统计便笺总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-07
     * @Description
     */
    @GetMapping(value = "totalCount")
    public Result<Long> totalCount(@RequestBody MemoEntity entity){
        return memoService.totalCount(entity);
    }

    /**
     * @Title 查询近半年便笺情况
     * @Params  [user]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.util.Map<java.lang.String,java.lang.String>>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-04-08
     * @Description
     */
    @GetMapping(value = "pre6Memo")
    public Result<Map<String,String>> countPre6Memo(@RequestParam(value = "user") String user){
        return memoService.countPre6Memo(user);
    }

}
