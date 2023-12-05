package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaResponseDataPojo {
    private List<SubscriptionFindByCriteriaResponseFilterPojo> data;

    public SubscriptionFindByCriteriaResponseDataPojo(List<SubscriptionFindByCriteriaResponseFilterPojo> data) {
        this.data = data;
    }
    public SubscriptionFindByCriteriaResponseDataPojo(){

    }

    public List<SubscriptionFindByCriteriaResponseFilterPojo> getData() {
        return data;
    }

    public void setData(List<SubscriptionFindByCriteriaResponseFilterPojo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponseDataPojo{" +
                "data=" + data +
                '}';
    }
    /*
    public SubscriptionFindByCriteriaResponseDataPojo(List<SubscriptionFindByCriteriaResponseFilterPojo> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
    public SubscriptionFindByCriteriaResponseDataPojo(){

    }
    public List<SubscriptionFindByCriteriaResponseFilterPojo> getSubscriptionList() {
        return subscriptionList;
    }


    public void setSubscriptionList(List<SubscriptionFindByCriteriaResponseFilterPojo> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponseDataPojo{" +
                "subscriptionList=" + subscriptionList +
                '}';
    }

 */
}
