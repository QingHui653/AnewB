package newb.c.a_spring.backend.redis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "work")
@Data
public class Work {
    @Id
    private String id;
    @Indexed
    private String name;
}
