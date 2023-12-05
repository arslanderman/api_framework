package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionFindByCriteriaRequestPojo {

    private Integer shopId;
    private Boolean isArchived;
    private String subscriptionSettingId;
    private String name;
    private Integer seasonId;
    private Boolean isActive;
    private String createdAtFrom;
    private String createdAtTo;

    public SubscriptionFindByCriteriaRequestPojo(Integer shopId, Boolean isArchived, String subscriptionSettingId, String name, Integer seasonId, Boolean isActive, String createdAtFrom, String createdAtTo) {
        this.shopId = shopId;
        this.isArchived = isArchived;
        this.subscriptionSettingId = subscriptionSettingId;
        this.name = name;
        this.seasonId = seasonId;
        this.isActive = isActive;
        this.createdAtFrom = createdAtFrom;
        this.createdAtTo = createdAtTo;
    }

    /*public SubscriptionFindByCriteriaRequestPojo(Integer shopId, Boolean isArchived, String subscription_Setting_Id, String name, Integer seasonId, Boolean isActive, String createdAtFrom, String createdAtTo) {
            this.shopId = shopId;
            this.isArchived = isArchived;
            this.subscriptionSettingId = subscriptionSettingId;
            this.name = name;
            this.seasonId = seasonId;
            this.isActive = isActive;
            this.createdAtFrom = createdAtFrom;
            this.createdAtTo = createdAtTo;
        }

         */
    public SubscriptionFindByCriteriaRequestPojo(){

    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean archived) {
        isArchived = archived;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedAtFrom() {
        return createdAtFrom;
    }

    //:public void setCreatedAtFrom(Date createdAtFrom) {
      //  this.createdAtFrom = createdAtFrom;
    //}


    public void setCreatedAtFrom(String createdAtFrom) {
        this.createdAtFrom = createdAtFrom;
    }

    public String getCreatedAtTo() {
        return createdAtTo;
    }

    public void setCreatedAtTo(String createdAtTo) {
        this.createdAtTo = createdAtTo;
    }

    //public void setCreatedAtTo(Date createdAtTo) {
      //  this.createdAtTo = createdAtTo;
    //}

    @Override
    public String toString() {
        return "SubscriptionFindByCriteriaRequestPojo{" +
                "shopId=" + shopId +
                ", isArchived=" + isArchived +
                ", subscriptionSettingId='" + subscriptionSettingId + '\'' +
                ", name='" + name + '\'' +
                ", seasonId=" + seasonId +
                ", isActive=" + isActive +
                ", createdAtFrom='" + createdAtFrom + '\'' +
                ", createdAtTo='" + createdAtTo + '\'' +
                '}';
    }





    /*  @Override
  public String toString() {
        return "SubscriptionFindByCriteriaRequestPojo{" +
                "shopId=" + shopId +
                ", isArchived=" + isArchived +
                ", subscription_Setting_Id='" + subscription_Setting_Id + '\'' +
                ", name='" + name + '\'' +
                ", seasonId=" + seasonId +
                ", isActive=" + isActive +
                ", createdAtFrom=" + createdAtFrom +
                ", createdAtTo=" + createdAtTo +
                '}';
    }

    */

}
