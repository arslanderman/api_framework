package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaResponseFilterPojo {

    private String subscriptionSettingId;
    private String name;
    private Integer seasonId;
    private Integer sessionId;
    private Boolean isActive;
    private String createdAt;

    public SubscriptionFindByCriteriaResponseFilterPojo(String subscriptionSettingId, String name, Integer seasonId, Integer sessionId, Boolean isActive, String createdAt) {
        this.subscriptionSettingId = subscriptionSettingId;
        this.name = name;
        this.seasonId = seasonId;
        this.sessionId = sessionId;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }
    public SubscriptionFindByCriteriaResponseFilterPojo(){

    }

    public String getSubscriptionSettingId() {
        return subscriptionSettingId;
    }

    public void setSubscriptionSettingId(String subscriptionSettingId) {
        this.subscriptionSettingId = subscriptionSettingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponseFilterPojo{" +
                "subscriptionSettingId='" + subscriptionSettingId + '\'' +
                ", name='" + name + '\'' +
                ", seasonId=" + seasonId +
                ", sessionId=" + sessionId +
                ", isActive=" + isActive +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
    /*
    public SubscriptionFindByCriteriaResponseFilterPojo(String subscriptionSettingId, String name, Integer seasonId, Integer sessionId, Boolean isActive, Date createdAt) {
        this.subscriptionSettingId = subscriptionSettingId;
        this.name = name;
        this.seasonId = seasonId;
        this.sessionId = sessionId;
        this.isActive = isActive;
        this.createdAt = createdAt;

    }
    public SubscriptionFindByCriteriaResponseFilterPojo(){

    }

    public String getSubscriptionSettingId() {
        return subscriptionSettingId;
    }

    public void setSubscriptionSettingId(String subscriptionSettingId) {
        this.subscriptionSettingId = subscriptionSettingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaResponsePojo{" +
                "subscriptionSettingId='" + subscriptionSettingId + '\'' +
                ", name='" + name + '\'' +
                ", seasonId=" + seasonId +
                ", sessionId=" + sessionId +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }

 */
}
