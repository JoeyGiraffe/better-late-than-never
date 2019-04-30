package orz.joey.web.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import orz.joey.service.dto.common.ErrorCode;
import orz.joey.service.dto.common.Response;
import orz.joey.service.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response defaultExceptionHandler(Exception e) {
        e.printStackTrace();

        if (e instanceof BusinessException) {
            return new Response<>(((BusinessException) e).getCode(), e.getMessage());
        }

        return new Response<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
