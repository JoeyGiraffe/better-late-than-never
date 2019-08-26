package orz.joey.web.common;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import orz.joey.service.dto.common.BaseResponse;
import orz.joey.service.exception.BusinessException;
import orz.joey.service.common.util.Loggers;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        if (e instanceof BusinessException) {
            Loggers.businessLog.error("业务异常", e);
            return new BaseResponse(((BusinessException) e).getCode(), e.getMessage());
        }

        Loggers.exceptionLog.error("非业务异常", e);

        if (e instanceof HttpRequestMethodNotSupportedException
                || e instanceof HttpMediaTypeNotSupportedException
                || e instanceof HttpMessageNotReadableException
                || e instanceof MethodArgumentTypeMismatchException) {
            return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), fieldErrors.get(0).getField()+fieldErrors.get(0).getDefaultMessage());
        }

        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constrainViolations = ((ConstraintViolationException) e).getConstraintViolations();
            return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), constrainViolations.iterator().next().getMessage());
        }

        if (e instanceof EmptyResultDataAccessException) {
            return new BaseResponse<>(HttpStatus.NO_CONTENT.value(), e.getMessage());
        }

        return new ModelAndView("error/500");
    }
}
