package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.MemoEntity;
import ac.cn.saya.lab.api.service.medium.MemoService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: MemoController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 10:01
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/memo")
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
    @GetMapping(value = "/one")
    public Result<MemoEntity> getOne(MemoEntity entity){
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
    public Result<Integer> delete(MemoEntity entity){
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
    @GetMapping(value = "/pagin")
    public Result<Object> getPage(MemoEntity entity){
        return memoService.getPage(entity);
    }

}
