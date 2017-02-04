package Model;

import java.io.Serializable;

/**
 * Created by don on 2016/4/26.
 */
public class BaseBean implements Serializable {

    private int status;
    private String info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
