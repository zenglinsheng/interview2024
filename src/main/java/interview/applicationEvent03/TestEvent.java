package interview.applicationEvent03;


import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
    private String message;

    public TestEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public TestEvent(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

