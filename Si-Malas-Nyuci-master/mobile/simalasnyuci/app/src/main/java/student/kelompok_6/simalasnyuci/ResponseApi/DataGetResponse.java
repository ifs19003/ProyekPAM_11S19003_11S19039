package student.kelompok_6.simalasnyuci.ResponseApi;

import student.kelompok_6.simalasnyuci.model.Laundry;

public class DataGetResponse {
    private String msg;
    private String code;
    private Laundry data;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public Laundry getData() {
        return data;
    }
}
