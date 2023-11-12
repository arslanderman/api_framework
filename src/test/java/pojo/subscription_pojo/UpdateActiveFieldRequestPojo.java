package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateActiveFieldRequestPojo {

    private String subscriptionSettingId;
    private Boolean isActive ;

    public UpdateActiveFieldRequestPojo(String subscriptionSettingId, Boolean isActive) {
        this.subscriptionSettingId = subscriptionSettingId;
        this.isActive = isActive;
    }
    public UpdateActiveFieldRequestPojo(){

    }

    public String getSubscriptionSettingId() {
        return subscriptionSettingId;
    }

    public void setSubscriptionSettingId(String subscriptionSettingId) {
        this.subscriptionSettingId = subscriptionSettingId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UpdateActiveFieldRequestPojo{" +
                "subscriptionSettingId='" + subscriptionSettingId + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
