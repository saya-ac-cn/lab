package ac.cn.saya.lab.medium.service.impl;

import ac.cn.saya.lab.api.entity.PictureEntity;
import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.service.medium.PictureStorageService;
import ac.cn.saya.lab.api.tools.*;
import ac.cn.saya.lab.medium.repository.PictureDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: PictureStorageServiceImpl
 * @ProjectName DataCenter
 * @Description: TODO
 * @Author Saya
 * @Date: 2020/3/13 23:03
 * @Description:
 */

@Service("pictureStorageService")
public class PictureStorageServiceImpl implements PictureStorageService {


    private static Logger logger = LoggerFactory.getLogger(PictureStorageServiceImpl.class);

    @Resource
    @Qualifier("pictureDAO")
    private PictureDAO pictureDAO;

    /**
     * @描述 图片上传服务（Base64）
     * @参数 [entity]
     * @返回值 java.lang.Integer
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> uploadPictureBase64(PictureEntity entity) {
        try {
            return ResultUtil.success(pictureDAO.insertPictuBase64(entity));
        } catch (Exception e) {
            logger.error("写入到图片表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 删除base64类型的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Integer> deletePictuBase64(PictureEntity entity) {
        try {
            return ResultUtil.success(pictureDAO.deletePictuBase64(entity));
        } catch (Exception e) {
            logger.error("删除图片表异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询分页后的图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<Object> getPictuBase64Page(PictureEntity entity) {
        try {
            long count = pictureDAO.getPictuBase64Count(entity);
            Result<Object> result = PageTools.page(count, entity, (condition) -> pictureDAO.getPictuBase64Page((PictureEntity) condition));
            return result;
        } catch (Exception e) {
            logger.error("查询分页后的图片发生异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

    /**
     * @描述 查询一张图片
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2020/3/13
     * @修改人和其它信息
     */
    @Override
    public Result<PictureEntity> getOnePictuBase64(PictureEntity entity) {
        try {
            return ResultUtil.success(pictureDAO.getOnePictuBase64(entity));
        } catch (Exception e) {
            logger.error("查询一张图片异常：" + Log4jUtils.getTrace(e));
            logger.error(CurrentLineInfo.printCurrentLineInfo());
            throw new MyException(ResultEnum.DB_ERROR);
        }
    }

}
