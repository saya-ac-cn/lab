package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.entity.UserEntity;
import ac.cn.saya.lab.api.tools.Result;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.feignclient.UserFeignClient;
import ac.cn.saya.lab.http.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: ExposeController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-02 17:19
 * @Description:
 */
@RestController
public class ExposeController {

    @Autowired
    private ICoreService coreService;



    @PostMapping(value = "/backend/login")
    public Result<Object> login(@RequestBody UserEntity user, HttpServletRequest request) throws Exception {
        return coreService.login(user, request);
    }




}
