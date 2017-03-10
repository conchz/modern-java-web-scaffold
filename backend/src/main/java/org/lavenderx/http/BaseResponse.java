package org.lavenderx.http;

import java.io.Serializable;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
public abstract class BaseResponse implements Serializable {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
