package interview.beanLifeCycle02;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private String name = "zls";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
