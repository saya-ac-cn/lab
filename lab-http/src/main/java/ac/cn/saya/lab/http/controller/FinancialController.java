package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.http.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: FinancialController
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 19:44
 * @Description:财政专用接口
 */
@RestController
public class FinancialController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/auth")
    public String home(){
        return "hi";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@RequestParam(value = "name") String name){
        return userFeignClient.getUser(name);
    }

}
