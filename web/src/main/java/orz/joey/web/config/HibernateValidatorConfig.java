package orz.joey.web.config;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;

@Configuration
public class HibernateValidatorConfig {

    /**
     * 添加MethodValidationPostProcessor拦截器
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public static Validator validator() {
        return Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true)//快速返回模式，有一个验证失败立即返回错误信息
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator("ValidationMessages_zh_CN")
//                                new AggregateResourceBundleLocator(Collections.singletonList("ValidationMessages.*"))
                        ))//自定义验证消息配置文件
                .buildValidatorFactory()
                .getValidator();
    }

}
