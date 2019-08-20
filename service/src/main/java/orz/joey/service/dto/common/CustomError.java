package orz.joey.service.dto.common;

public enum CustomError {

    ARGUMENT_TYPE_MISMATCH(400, "Argument Type Mismatch"),
    REQUEST_BODY_MISSING(400, "Request Body Missing"),
    MEDIA_TYPE_NOT_SUPPORTED(400, "Media Type Not Supported"),

    /*用户模块*/
    USER_NOT_FOUND(10001, "用户不存在"),
    USER_DELETE_SUCCESS(10002, "用户删除成功"),
    USER_DELETE_FAIL(10003, "用户删除失败"),


    ;
    private int code;
    private String msg;

    CustomError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
