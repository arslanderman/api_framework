package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DenemeOuterPojo {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private DenemeInnerPojo bookingdates;
    private String additionalneeds;

    public DenemeOuterPojo(String firstname, String lastname, Integer totalprice, Boolean depositpaid, DenemeInnerPojo bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public DenemeOuterPojo(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public DenemeInnerPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(DenemeInnerPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "DenemeOuterPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
