package newb.c.a_spring.backend.redis.service;

import newb.c.a_spring.backend.redis.dao.WorkDao;
import newb.c.a_spring.backend.redis.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService {

    @Autowired(required = false)
    private WorkDao workDao;

    public void addWork(){
        Work work =new Work();
        List<Work> workList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            work.setId(String.valueOf(i));
            work.setName(String.valueOf(i));
            workList.add(work);
        }
        workDao.save(workList);
    }

    public Iterable findAll(){
        return workDao.findAll();
    }
}
