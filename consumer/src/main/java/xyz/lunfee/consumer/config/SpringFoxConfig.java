package xyz.lunfee.consumer.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lunfee
 * @create 2021/12/26-10:29
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "xyz.lunfee.consumer.controller"))
                .paths(PathSelectors.any())
                .build();
    }

   private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Lunfee","lunfee.top", "lunfee@whut.edu.cn"))
                .title(applicationName)
                .description("提供Rabbit-Stream 项目中所用的API, 供调试......")
                .version("1.0")
                .build();
    }
}
