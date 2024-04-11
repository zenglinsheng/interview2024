package interview.iocFacade01.pay;

import interview.iocFacade01.SupportType;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class WeixinPay implements PayFacade {
    @Override
    public void pay() {
        System.err.println("微信支付...");
    }

    @Override
    public SupportType getSupportType() {
        SupportType supportType = new SupportType();
        supportType.setBusinessType("pay");
        supportType.setTemplateCode("weixin");
        return supportType;
    }

}
