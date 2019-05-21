package orz.joey.web.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import orz.joey.service.dto.common.CustomError;
import orz.joey.service.dto.common.Response;
import orz.joey.service.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Response businessExceptionHandler(BusinessException e) {
        e.printStackTrace();
        return new Response(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Response methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return new Response(CustomError.ARGUMENT_TYPE_MISMATCH);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        ModelAndView m = new ModelAndView();
        m.setViewName("error/500");

        return m;
    }
}
