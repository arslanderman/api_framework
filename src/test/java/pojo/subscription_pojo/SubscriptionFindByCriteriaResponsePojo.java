package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaResponsePojo {

    private SubscriptionFindByCriteriaResponse_XPojo _links;
    private Integer count;
    private Integer total;
    private List<SubscriptionFindByCriteriaResponseFilterPojo> data;

    public SubscriptionFindByCriteriaResponsePojo(SubscriptionFindByCriteriaResponse_XPojo _links, Integer count, Integer total, List<SubscriptionFindByCriteriaResponseFilterPojo> data) {
        this._links = _links;
        this.count = count;
        this.total = total;
        this.data = data;
    }
    public SubscriptionFindByCriteriaResponsePojo(){

    }

    public SubscriptionFindByCriteriaResponse_XPojo get_links() {
        return _links;
    }

    public void set_links(SubscriptionFindByCriteriaResponse_XPojo _links) {
        this._links = _links;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SubscriptionFindByCriteriaResponseFilterPojo> getData() {
        return data;
    }

    public void setData(List<SubscriptionFindByCriteriaResponseFilterPojo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponsePojo{" +
                "_links=" + _links +
                ", count=" + count +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
    /*
    public SubscriptionFindByCriteriaResponsePojo(SubscriptionFindByCriteriaResponse_XPojo _links, Integer count, Integer total, SubscriptionFindByCriteriaResponseDataPojo data) {
        this._links = _links;
        this.count = count;
        this.total = total;
        this.data = data;
    }
    public SubscriptionFindByCriteriaResponsePojo(){

    }

    public SubscriptionFindByCriteriaResponsePojo(SubscriptionFindByCriteriaResponse_XPojo href) {

    }

    public SubscriptionFindByCriteriaResponse_XPojo get_links() {
        return _links;
    }

    public void set_links(SubscriptionFindByCriteriaResponse_XPojo _links) {
        this._links = _links;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public SubscriptionFindByCriteriaResponseDataPojo getData() {
        return data;
    }

    public void setData(SubscriptionFindByCriteriaResponseDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponsePojo{" +
                "_links=" + _links +
                ", count=" + count +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

 */
}
