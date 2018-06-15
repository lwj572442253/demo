package com.lwj.example.util;

public class JsonResult {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private Object data;
    private String result;
    private String message;

    public JsonResult() {
    }

    public JsonResult(Object data, String message){
        this.result = JsonResult.SUCCESS;
        this.data = data;
        this.message = message;
    }

    public JsonResult(String message){
        this.result = JsonResult.ERROR;
        this.data = "";
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
