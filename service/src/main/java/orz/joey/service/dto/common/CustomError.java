package orz.joey.service.dto.common;

public enum CustomError {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),


    USER_NOT_FOUND(10000, "User not found"),


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

    public static CustomError fromCode(int code) {
        CustomError[] values = CustomError.values();
        for (CustomError val : values) {
            if (val.getCode() == code) return val;
        }

        return INTERNAL_SERVER_ERROR;
    }
}
