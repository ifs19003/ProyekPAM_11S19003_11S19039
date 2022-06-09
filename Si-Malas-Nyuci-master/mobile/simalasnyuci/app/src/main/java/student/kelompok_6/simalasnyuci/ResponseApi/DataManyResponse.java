package student.kelompok_6.simalasnyuci.ResponseApi;

import java.util.LinkedList;
import java.util.List;

import student.kelompok_6.simalasnyuci.model.User;

public class DataManyResponse {
    private String status;
    private String code;
    private LinkedList<User> data = new LinkedList<>();

    public String getMsg() {
        return status;
    }

    public void setMsg(String msg) {
        this.status = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LinkedList<User> getUsers() {
        return data;
    }

    public void setUsers(LinkedList<User> users) {
        this.data = users;
    }
}
