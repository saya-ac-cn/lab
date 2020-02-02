package ac.cn.saya.lab.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/home")
    public String home(){
        return "home~";
    }
}
