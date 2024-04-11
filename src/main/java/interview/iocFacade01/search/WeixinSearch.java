package interview.iocFacade01.search;

import interview.iocFacade01.SupportType;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class WeixinSearch implements SearchFacade {
    @Override
    public void search() {
        System.err.println("微信搜索...");
    }

    @Override
    public SupportType getSupportType() {
        SupportType supportType = new SupportType();
        supportType.setBusinessType("search");
        supportType.setTemplateCode("weixin");
        return supportType;
    }

}
