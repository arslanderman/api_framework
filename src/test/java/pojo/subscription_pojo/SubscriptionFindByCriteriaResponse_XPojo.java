package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaResponse_XPojo {
    private SubscriptionFindByCriteriaResponseHrefPojo self;
    private SubscriptionFindByCriteriaResponseHrefPojo first;
    private SubscriptionFindByCriteriaResponseHrefPojo prev;
    private SubscriptionFindByCriteriaResponseHrefPojo next;
    private SubscriptionFindByCriteriaResponseHrefPojo last;

    public SubscriptionFindByCriteriaResponse_XPojo(SubscriptionFindByCriteriaResponseHrefPojo self, SubscriptionFindByCriteriaResponseHrefPojo first, SubscriptionFindByCriteriaResponseHrefPojo prev, SubscriptionFindByCriteriaResponseHrefPojo next, SubscriptionFindByCriteriaResponseHrefPojo last) {
        this.self = self;
        this.first = first;
        this.prev = prev;
        this.next = next;
        this.last = last;
    }
    public SubscriptionFindByCriteriaResponse_XPojo(){

    }

    public SubscriptionFindByCriteriaResponse_XPojo(SubscriptionFindByCriteriaResponseHrefPojo href) {
    }

    public SubscriptionFindByCriteriaResponseHrefPojo getSelf() {
        return self;
    }

    public void setSelf(SubscriptionFindByCriteriaResponseHrefPojo self) {
        this.self = self;
    }

    public SubscriptionFindByCriteriaResponseHrefPojo getFirst() {
        return first;
    }

    public void setFirst(SubscriptionFindByCriteriaResponseHrefPojo first) {
        this.first = first;
    }

    public SubscriptionFindByCriteriaResponseHrefPojo getPrev() {
        return prev;
    }

    public void setPrev(SubscriptionFindByCriteriaResponseHrefPojo prev) {
        this.prev = prev;
    }

    public SubscriptionFindByCriteriaResponseHrefPojo getNext() {
        return next;
    }

    public void setNext(SubscriptionFindByCriteriaResponseHrefPojo next) {
        this.next = next;
    }

    public SubscriptionFindByCriteriaResponseHrefPojo getLast() {
        return last;
    }

    public void setLast(SubscriptionFindByCriteriaResponseHrefPojo last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteria_LinksPojo{" +
                "self=" + self +
                ", first=" + first +
                ", prev=" + prev +
                ", next=" + next +
                ", last=" + last +
                '}';
    }
}
