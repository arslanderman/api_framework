package pojo.api_automation_pojo;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class FindAllListPojo {


    private List<Object> data; // instead of object can i use tag group pojo related to response

    public FindAllListPojo() {
    }
    public FindAllListPojo(List<Object> data) {
        this.data = data;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FindAllEntityTypesPojo{" +
                "data=" + data +
                '}';
    }


    /*
      private List<TagAndTagGroupPojo> data;

    public FindAllListPojo() {

    }

    public FindAllListPojo(List<TagAndTagGroupPojo> data) {
        this.data = data;
    }



    public List<TagAndTagGroupPojo> getData() {
        return data;
    }

    public void setData(List<TagAndTagGroupPojo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FindAllListPojo{" +
                "data=" + data +
                '}';
    }

     */
}
