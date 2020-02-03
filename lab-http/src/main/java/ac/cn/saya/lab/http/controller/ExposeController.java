package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.service.financial.TransactionReadService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TransactionReadService transactionReadService;

    @GetMapping(value = "/home")
    public String home(){
        return "home~";
    }

    @GetMapping(value = "/type")
    public Object type(){
        return transactionReadService.selectTransactionType();
    }

}
