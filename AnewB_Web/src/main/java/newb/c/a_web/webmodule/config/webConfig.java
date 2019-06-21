package newb.c.a_web.webmodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class webConfig {
    @Bean
    public mappingProcessor mappingProcessor(){
        return new mappingProcessor();
    }

}
