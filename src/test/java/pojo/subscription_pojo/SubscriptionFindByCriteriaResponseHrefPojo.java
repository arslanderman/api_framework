package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaResponseHrefPojo {
    private  String href;

    public SubscriptionFindByCriteriaResponseHrefPojo(String href) {
        this.href = href;
    }
    public SubscriptionFindByCriteriaResponseHrefPojo(){

    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponseHrefPojo{" +
                "href='" + href + '\'' +
                '}';
    }
}
