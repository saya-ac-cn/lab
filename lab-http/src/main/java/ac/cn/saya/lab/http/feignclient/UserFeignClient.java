package ac.cn.saya.lab.http.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: UserFeignClient
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 14:50
 * @Description:
 */
@FeignClient(name = "lab-medium-server")
public interface UserFeignClient {

    @GetMapping("/server/hello")
    public String getUser(@RequestParam(value = "name")String name);

}
