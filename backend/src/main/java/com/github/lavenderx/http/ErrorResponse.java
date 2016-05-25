package com.github.lavenderx.http;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {

    }

    public ErrorResponse(String status, String message) {
        super.setStatus(status);
        super.setMessage(message);
    }
}
