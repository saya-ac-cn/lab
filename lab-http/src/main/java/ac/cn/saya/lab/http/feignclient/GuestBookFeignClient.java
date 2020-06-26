package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.GuestBookEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Title: GuestBookFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 09:53
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "guestbook",path = "medium/guestbook")
public interface GuestBookFeignClient {

    /**
     * @描述 留言
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> insertGuestBook(@RequestBody GuestBookEntity entity);

    /**
     * @描述 审核修改
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Integer> updateGuestBook(@RequestBody GuestBookEntity entity);

    /**
     * @描述 查询一条留言
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.GuestBookEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<GuestBookEntity> queryOneGuestBook(@RequestBody GuestBookEntity entity);

    /**
     * @描述 获取分页后的留言
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getGuestBookPage(@RequestBody GuestBookEntity entity);

}
