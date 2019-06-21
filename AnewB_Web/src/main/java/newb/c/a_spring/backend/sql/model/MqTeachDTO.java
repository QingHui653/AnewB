package newb.c.a_spring.backend.sql.model;

import lombok.Data;
import newb.c.a_spring.backend.sql.model.basemodel.MqStudent;
import newb.c.a_spring.backend.sql.model.basemodel.MqTeach;

@Data
public class MqTeachDTO {

    private MqTeach mqTeach;

    private MqStudent mqStudent;

}
