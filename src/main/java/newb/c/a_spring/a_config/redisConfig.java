package newb.c.a_spring.a_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = {"new.c.backend.redis"})
public class redisConfig {

}
