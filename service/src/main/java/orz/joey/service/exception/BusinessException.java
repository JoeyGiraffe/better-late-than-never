package orz.joey.service.exception;

import orz.joey.service.dto.common.constant.CustomError;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4139359723330306681L;
    private int code;

    public BusinessException(CustomError error) {
        super(error.getMsg());
        this.code = error.getCode();
    }

    public int getCode() {
        return code;
    }

}

