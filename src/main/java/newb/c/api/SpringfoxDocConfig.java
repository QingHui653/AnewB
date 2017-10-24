package newb.c.api;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * 使用java配置是全部需要
 * 使用xml配置 开启@enableswagger2即可，单需要在mvc.xml中注册
 */
//@Configuration
//@ComponentScan("newb.c.controller")
//@EnableWebMvc
@EnableSwagger2
public class SpringfoxDocConfig {
    /**
     * 访问/swagger-ui.html
     * @return
     */
    @Bean
    public Docket configSpringfoxDocketForAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AnewB的API目录页面")
                .description("可查看所有的api说明")
                .termsOfServiceUrl("AnewB")
                .license("i just want run")
                .licenseUrl("https://github.com/QingHui653")
                .version("9999999")
                .build();
    }
}
