package newb.c.a_spring.backend.redis.dao;

import newb.c.a_spring.backend.redis.model.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDao extends CrudRepository<Work,String> {
}
