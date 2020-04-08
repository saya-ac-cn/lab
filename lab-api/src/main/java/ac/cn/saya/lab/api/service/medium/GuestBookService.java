package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.GuestBookEntity;
import ac.cn.saya.lab.api.tools.Result;

import java.util.Map;

/**
 * @Title: GuestBookService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-12 21:09
 * @Description:
 */

public interface GuestBookService {

    /**
     * @描述 留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> insertGuestBook(GuestBookEntity entity);

    /**
     * @描述 审核修改
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Integer> updateGuestBook(GuestBookEntity entity);

    /**
     * @描述 查询一条留言
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.entity.GuestBookEntity
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<GuestBookEntity> queryOneGuestBook(GuestBookEntity entity);

    /**
     * @描述 获取分页后的留言
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/12
     * @修改人和其它信息
     */
    public Result<Object> getGuestBookPage(GuestBookEntity entity);

}
