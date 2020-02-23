package ac.cn.saya.lab.http.controller;

import ac.cn.saya.lab.api.entity.TransactionListEntity;
import ac.cn.saya.lab.api.service.financial.TransactionReadService;
import ac.cn.saya.lab.api.service.financial.TransactionWriteService;
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

    @Autowired
    private TransactionWriteService transactionWriteService;

    @GetMapping(value = "/home")
    public String home(){
        return "home~";
    }

    @GetMapping(value = "/type")
    public Object type(){
        return transactionReadService.selectTransactionType();
    }

    @GetMapping(value = "/test")
    public Object test(){
        TransactionListEntity entity = new TransactionListEntity();
        entity.setDeposited(0.0);
        entity.setSource("Pandora");
        entity.setExpenditure(10.0);
        entity.setTradeDate("2020-02-02");
        entity.setTradeType(1);
        entity.setCurrencyNumber(10.0);
        entity.setTransactionAmount("生活开支");
        return transactionWriteService.insertTransactionList(entity);
    }

}
