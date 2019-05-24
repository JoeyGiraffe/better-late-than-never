package orz.joey.service.dto.common;

public enum CustomError {

    ARGUMENT_TYPE_MISMATCH(400, "Argument Type Mismatch"),
    REQUEST_BODY_MISSING(400, "Request Body Missing"),
    MEDIA_TYPE_NOT_SUPPORTED(400, "Media Type Not Supported"),


    USER_NOT_FOUND(10001, "User Not Found"),
    ADD_USER_FAILED(10002, "Add User Failed"),

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
