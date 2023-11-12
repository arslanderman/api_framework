package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCreateRequestPojo {

    private Integer shopId;
    private String name;

    public SubscriptionCreateRequestPojo(Integer shopId, String name) {
        this.shopId = shopId;
        this.name = name;
    }
    public SubscriptionCreateRequestPojo(){

    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubscriptionCreateRequestPojo{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
