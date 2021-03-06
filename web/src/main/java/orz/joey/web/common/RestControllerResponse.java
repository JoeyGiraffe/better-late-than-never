package orz.joey.web.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import orz.joey.service.dto.common.BaseResponse;
import orz.joey.service.dto.common.constant.CustomError;

// 指向所有带有注解@RestController的控制器
@ControllerAdvice(annotations = RestController.class)
public class RestControllerResponse implements ResponseBodyAdvice {

    private final ObjectMapper mapper;

    @Autowired
    public RestControllerResponse(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        return !converterType.equals(StringHttpMessageConverter.class);
        return true;//总是执行beforeBodyWrite()
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof BaseResponse) {
            return body;
        }
        if (body instanceof CustomError) {
            return new BaseResponse<>((CustomError) body);
        }
        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            try {
                return mapper.writeValueAsString(new BaseResponse<>(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return new BaseResponse<>(body);
    }
}
