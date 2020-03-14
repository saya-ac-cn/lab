package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.GuestBookEntity;
import ac.cn.saya.lab.api.service.medium.GuestBookService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: GuestBookController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 09:53
 * @Description:
 */
@RestController
@RequestMapping(value = "/medium/guestbook")
public class GuestBookController {

    @Autowired
    private GuestBookService guestBookService;

    /**
     * @描述 留言
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Object> insertGuestBook(@RequestBody GuestBookEntity entity){
        return guestBookService.insertGuestBook(entity);
    }

    /**
     * @描述 审核修改
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PutMapping(value = "/")
    public Result<Object> updateGuestBook(@RequestBody GuestBookEntity entity){
        return guestBookService.updateGuestBook(entity);
    }

    /**
     * @描述 查询一条留言
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/one")
    public Result<Object> queryOneGuestBook(GuestBookEntity entity){
        return guestBookService.queryOneGuestBook(entity);
    }

    /**
     * @描述 获取分页后的留言
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/pagin")
    public Result<Object> getGuestBookPage(GuestBookEntity entity){
        return guestBookService.getGuestBookPage(entity);
    }

}
