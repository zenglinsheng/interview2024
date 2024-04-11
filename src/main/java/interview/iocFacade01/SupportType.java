package interview.iocFacade01;

import java.util.Objects;

public class SupportType {

    private String businessType;

    private String templateCode;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportType that = (SupportType) o;
        return Objects.equals(businessType, that.businessType) && Objects.equals(templateCode, that.templateCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessType, templateCode);
    }

}
