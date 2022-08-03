package uz.upay.transactionmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TransactionManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void list() {
        List<String> names = new ArrayList<>(10);
        names.add("Ali");
        names.add("Vali");
        names.add("Qosim");
        names.add("Bekzod");
        names.add("Qurbon");
        names.add("Zokir");
        names.add("Aziz");
        names.add("Islom");
        names.add("Umar");
        names.add("Usmon");
        System.out.println(names);

        names.forEach((name) -> {
            if (name.charAt(0) == 'A') {
                names.remove(name);
            }
            System.out.println("names: "+names);
        });
    }

}
