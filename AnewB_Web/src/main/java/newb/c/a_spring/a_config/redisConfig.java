package newb.c.a_spring.a_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = {"newb.c.a_spring.backend.redis"})
public class redisConfig {

}
