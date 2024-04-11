package interview.iocFacade01.search;

import interview.iocFacade01.SupportType;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class AliSearch implements SearchFacade {
    @Override
    public void search() {
        System.err.println("阿里搜索...");
    }

    @Override
    public SupportType getSupportType() {
        SupportType supportType = new SupportType();
        supportType.setBusinessType("search");
        supportType.setTemplateCode("ali");
        return supportType;
    }

}
