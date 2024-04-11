package interview.beanLifeCycle02;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Teacher implements BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {
    private final Student student;

    public Teacher(Student student) {
        System.err.println("执行构造方法...");
        this.student = student;
    }

    public void printStudentName() {
        System.out.println("studentName=" + student.getName());
    }

    @Override
    public void setBeanName(String beanName) {
        System.err.println("BeanNameAware, beanName = " + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("ApplicationContextAware, applicationContext = " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("InitializingBean.afterPropertiesSet...");
//        System.err.println("before studentName = " + student.getName());
//        student.setName("hjy");
//        System.err.println("before studentName = " + student.getName());
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("DisposableBean.destroy...");
    }

    public void initMethod() {
        System.err.println("initMethod, 自定义init...");
    }

    public void destroyMethod() {
        System.err.println("destroyMethod, 自定义destroy...");
    }

    @PostConstruct
    public void postConstruct() {
        System.err.println("PostConstruct(Java servlet自带)...");
    }

    @PreDestroy
    public void preDestroy() {
        System.err.println("PreDestroy(Java servlet自带)...");
    }

}
