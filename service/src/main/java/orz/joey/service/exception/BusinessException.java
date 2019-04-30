package orz.joey.service.exception;

import orz.joey.service.dto.common.ErrorCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4139359723330306681L;
    private int code;

    public BusinessException() {}

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }

}

