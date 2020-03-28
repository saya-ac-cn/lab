package ac.cn.saya.lab.api.service.medium;

import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.tools.Result;

/**
 * @Title: PictureStorageService
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-13 20:41
 * @Description:
 */

public interface PictureStorageService {

    /**
     * @描述 图片上传服务（Base64）
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> uploadPictureBase64(PictureEntity entity);

    /**
     * @描述 删除base64类型的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Integer> deletePictuBase64(PictureEntity entity);

    /**
     * @描述 查询分页后的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<Object> getPictuBase64Page(PictureEntity entity);

    /**
     * @描述 查询一张图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    public Result<PictureEntity> getOnePictuBase64(PictureEntity entity);

    /**
     * @描述 查询图片的总数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019/1/12
     * @修改人和其它信息
     */
    public Result<Long> getPictuBase64Count(PictureEntity entity);
}
