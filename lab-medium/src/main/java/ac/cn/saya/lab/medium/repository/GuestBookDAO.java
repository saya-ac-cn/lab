package ac.cn.saya.lab.medium.repository;

import ac.cn.saya.lab.api.entity.GuestBookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: NewsDAO
 * @ProjectName lab
 * @Description: TODO
 * @Author saya.ac.cn-刘能凯
 * @Date: 2020-02-29
 * @Description:
 * 留言DAO
 */

@Mapper
public interface GuestBookDAO {

    /**
     * @描述 留言
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer insertGuestBook(GuestBookEntity entity);

    /**
     * @描述 审核回复
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Integer updateGuestBook(GuestBookEntity entity);


    /**
     * @描述 查询一条留言
     * @参数  
     * @返回值  
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public GuestBookEntity getOneGuestBook(GuestBookEntity entity);

    /**
     * @描述 获取分页后的留言
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public List<GuestBookEntity> getGuestBookPage(GuestBookEntity entity);

    /**
     * @描述 获取留言总数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/11
     * @修改人和其它信息
     */
    public Long getGuestBookCount(GuestBookEntity entity);

}
