package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DenemeInnerPojo {

    private String checkin;
    private String checkout;

    public DenemeInnerPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public DenemeInnerPojo(){

    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "DenemeInnerPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
