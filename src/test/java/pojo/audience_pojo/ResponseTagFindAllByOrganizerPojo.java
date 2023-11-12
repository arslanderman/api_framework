package pojo.audience_pojo;

import java.util.List;

public class ResponseTagFindAllByOrganizerPojo {

    private List<ResponseTagFindAllInnerPojo> data;

    public ResponseTagFindAllByOrganizerPojo(List<ResponseTagFindAllInnerPojo> data) {
        this.data = data;
    }

    public ResponseTagFindAllByOrganizerPojo(){

    }

    public List<ResponseTagFindAllInnerPojo> getData() {
        return data;
    }

    public void setData(List<ResponseTagFindAllInnerPojo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseTagFindAllByOrganizerPojo{" +
                "data=" + data +
                '}';
    }
}
