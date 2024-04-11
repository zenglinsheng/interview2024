package interview.iocFacade01.pay;

import interview.iocFacade01.SupportType;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class AliPay implements PayFacade {
    @Override
    public void pay() {
        System.err.println("阿里支付...");
    }

    @Override
    public SupportType getSupportType() {
        SupportType supportType = new SupportType();
        supportType.setBusinessType("pay");
        supportType.setTemplateCode("ali");
        return supportType;
    }

}
