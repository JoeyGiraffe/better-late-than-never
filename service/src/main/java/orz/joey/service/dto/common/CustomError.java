package orz.joey.service.dto.common;

public enum CustomError {

    ARGUMENT_TYPE_MISMATCH(400, "Argument Type Mismatch"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),


    USER_NOT_FOUND(10001, "User Not Found"),

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
