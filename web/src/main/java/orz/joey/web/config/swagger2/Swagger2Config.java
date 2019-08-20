package orz.joey.web.config.swagger2;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import orz.joey.service.dto.UserDto;
import orz.joey.service.dto.common.BaseResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    private final TypeResolver typeResolver;

    @Autowired
    public Swagger2Config(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("(?!/error).*"))
                .build().apiInfo(apiEndPointsInfo())
//                .directModelSubstitute()
                .genericModelSubstitutes(ResponseEntity.class)
                //自定义Api Model转化规则
                //DeferredResult<ResponseEntity<BaseResponse<XxxDto>>>
//                .alternateTypeRules(
//                        newRule(typeResolver.resolve(DeferredResult.class,
//                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                                typeResolver.resolve(WildcardType.class)))
//                .forCodeGeneration(false)//设置Generic Types命名策略
                ;
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Api Documentation Created By Swagger")
                .contact(new Contact("JoeyGiraffe", "http://www.handsomezhu.me", "joey.googling@gmail.com"))
                .license("MIT License")
                .licenseUrl("https://github.com/JoeyGiraffe/better-late-than-never/blob/master/LICENSE")
                .version("1.0.0")
                .build();
    }

}
