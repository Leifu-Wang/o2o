package com.hust.o2o.dto;

/**
 * @author: wang
 * @Desciption:
 * @Date: Created in 15:45 2019/1/19
 * @Modified By:
 **/
public class Result<T> {

    private boolean success;
    private T data;
    private String errMsg;
    private int errorCode;

    /**
     * 返回正确时的构造器
     * @param success true
     * @param data json 数据
     */
    public Result(boolean success, T data){
        this.success = success;
        this.data= data;
    }

    /**
     * 返回错误结果时的构造器
     * @param success false
     * @param errMsg 错误信息
     * @param errorCode 错误码
     */
    public Result(boolean success, String errMsg, int errorCode) {
        this.success = success;
        this.errMsg = errMsg;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
