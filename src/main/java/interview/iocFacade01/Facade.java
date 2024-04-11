package interview.iocFacade01;

import org.springframework.beans.factory.InitializingBean;

public interface Facade extends InitializingBean {

    SupportType getSupportType();

    @Override
    default void afterPropertiesSet() throws Exception {
        Factory.register(getSupportType(), this);
    }

}
