package ac.cn.saya.lab.http;

import ac.cn.saya.lab.financial.repository.TransactionReadDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(classes = LabHttpApplication.class)
class LabHttpApplicationTests {

    @Autowired
    private TransactionReadDAO transactionReadDAO;

    @Test
    void contextLoads() {
    }

    @Test
    public void read(){
        transactionReadDAO.selectTransactionType();
    }

}
