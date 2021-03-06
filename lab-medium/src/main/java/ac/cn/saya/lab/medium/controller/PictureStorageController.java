package ac.cn.saya.lab.medium.controller;

import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.service.medium.PictureStorageService;
import ac.cn.saya.lab.api.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: PictureStorageController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-14 13:42
 * @Description:
 */
@RestController
@RequestMapping(value = "medium/picturestorage")
public class PictureStorageController {

    @Autowired
    private PictureStorageService pictureStorageService;

    /**
     * @描述 图片上传服务（Base64）
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @PostMapping(value = "/")
    public Result<Integer> uploadPictureBase64(@RequestBody PictureEntity entity){
        return pictureStorageService.uploadPictureBase64(entity);
    }

    /**
     * @描述 删除base64类型的图片
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Integer>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @DeleteMapping(value = "/")
    public Result<Integer> deletePictuBase64(@RequestBody PictureEntity entity){
        return pictureStorageService.deletePictuBase64(entity);
    }

    /**
     * @描述 查询分页后的图片
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<java.lang.Object>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "pagin")
    public Result<Object> getPictuBase64Page(@RequestBody PictureEntity entity){
        return pictureStorageService.getPictuBase64Page(entity);
    }

    /**
     * @描述 查询一张图片
     * @参数  [entity]
     * @返回值  ac.cn.saya.lab.api.tools.Result<ac.cn.saya.lab.api.entity.PictureEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-14
     * @修改人和其它信息
     */
    @GetMapping(value = "one")
    public Result<PictureEntity> getOnePictuBase64(@RequestBody PictureEntity entity){
        return pictureStorageService.getOnePictuBase64(entity);
    }

    /**
     * @Title 查询图片的总数
     * @Params  [entity]
     * @Return  ac.cn.saya.lab.api.tools.Result<java.lang.Long>
     * @Author  saya.ac.cn-刘能凯
     * @Date  2020-03-28
     * @Description
     */
    @GetMapping(value = "count")
    public Result<Long> getPictuBase64Count(@RequestBody PictureEntity entity){
        return pictureStorageService.getPictuBase64Count(entity);
    }

}
