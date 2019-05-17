package orz.joey.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 进入Controller之前，譬如请求一个不存在的地址
 **/
@Controller
@RequestMapping("/error")
public class ErrorPageController implements ErrorController {

    private static final String ERROR_PATH = "error/404";

    @RequestMapping
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
