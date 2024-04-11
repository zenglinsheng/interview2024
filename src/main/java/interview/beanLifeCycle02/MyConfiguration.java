package interview.beanLifeCycle02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean(name = "teacher", initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Teacher getTeacher(Student student) {
        return new Teacher(student);
    }

}
