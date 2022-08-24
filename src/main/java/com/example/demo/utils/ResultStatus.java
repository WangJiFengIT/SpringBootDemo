package com.example.demo.utils;

public enum ResultStatus {
    OK(0, "OK"),
    BAD_REQUEST(1, "请求失败"),
    USER_NOT_FOUND(1, "用户不存在!"),
    PASSWORD_ERROR(1, "密码错误!"),
    PASSWORD_INTENSITY_ERROR(1, "密码至少6位且包含大小写和数字!"),
    FILE_SUCCESS(0, "文件上传成功!"),
    FILE_ERROR(1, "文件上传失败!"),
    LACK_PARAM_PAGE(1, "缺少分页参数!"),
    NUMBER_REPEAT(1, "排序不能重复!"),
    SIGN_ERROR(1, "签名错误!"),
    DOCUMENT_ERROR(1, "文档解析错误!"),
    ASSET_NO_EXIST(1, "资源不存在!"),
    LACK_PARAM(1, "缺少参数!"),
    BUSYNESS(1, "当前系统繁忙,请稍后再试");


    private int code;
    private String message;

    private ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
