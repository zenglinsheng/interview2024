package interview.applicationEvent03;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TestEventPublish {

    private final ApplicationEventPublisher applicationEventPublisher;

    public TestEventPublish(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish() {
        applicationEventPublisher.publishEvent(new TestEvent("测试..."));
    }

}
