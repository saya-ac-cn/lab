package ac.cn.saya.lab.http.feignclient;

import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Title: PictureStorageFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:42
 * @Description:
 */
@FeignClient(value = "lab-medium-server", contextId = "picturestorage")
public interface PictureStorageFeignClient {


    /**
     * @描述 图片上传服务（Base64）
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/medium/picturestorage")
    public Result<Integer> uploadPictureBase64(@RequestBody PictureEntity entity);

    /**
     * @描述 删除base64类型的图片
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/medium/picturestorage")
    public Result<Integer> deletePictuBase64(PictureEntity entity);

    /**
     * @描述 查询分页后的图片
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/picturestorage/pagin")
    public Result<Object> getPictuBase64Page(PictureEntity entity);

    /**
     * @描述 查询一张图片
     * @参数 [entity]
     * @返回值 ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.PictureEntity>
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "/medium/picturestorage/one")
    public Result<PictureEntity> getOnePictuBase64(PictureEntity entity);

}
