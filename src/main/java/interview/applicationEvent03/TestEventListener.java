package interview.applicationEvent03;

import lombok.extern.log4j.Log4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Log4j
public class TestEventListener {

    @EventListener(TestEvent.class)
    @Order(1)
    public void testEvent01(TestEvent testEvent) {
//        System.err.println("触发时间监听机制01, message = " + testEvent.getMessage());
        log.info("触发时间监听机制01, message = " + testEvent.getMessage());
    }

    @EventListener(TestEvent.class)
    @Order(2)
    public void testEvent02(TestEvent testEvent) {
//        System.err.println("触发时间监听机制02, message = " + testEvent.getMessage());
        log.info("触发时间监听机制02, message = " + testEvent.getMessage());
    }

}
