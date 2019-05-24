package orz.joey.web.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import orz.joey.service.dto.common.CustomError;
import orz.joey.service.dto.common.Response;
import orz.joey.service.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        e.printStackTrace();
        return new Response(CustomError.MEDIA_TYPE_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new Response(CustomError.REQUEST_BODY_MISSING);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return new Response(CustomError.ARGUMENT_TYPE_MISMATCH);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return new Response(HttpStatus.BAD_REQUEST.value(), fieldErrors.get(0).getField()+fieldErrors.get(0).getDefaultMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationExceptionHandler(ConstraintViolationException e) {
        e.printStackTrace();
        Set<ConstraintViolation<?>> constrainViolations = e.getConstraintViolations();
        return new Response(HttpStatus.BAD_REQUEST.value(), constrainViolations.iterator().next().getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response businessExceptionHandler(BusinessException e) {
        e.printStackTrace();
        return new Response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        ModelAndView m = new ModelAndView();
        m.setViewName("error/500");

        return m;
    }
}
