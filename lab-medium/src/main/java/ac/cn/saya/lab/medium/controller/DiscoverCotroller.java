package ac.cn.saya.lab.medium.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: DiscoverCotroller
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 13:46
 * @Description:
 */
@RestController
@RequestMapping("server")
public class DiscoverCotroller {

    /**
     * 对外提供的服务 HTTP接口
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return "hello " + name;
    }

}
