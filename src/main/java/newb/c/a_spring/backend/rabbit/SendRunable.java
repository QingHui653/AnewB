package newb.c.a_spring.backend.rabbit;

import newb.c.a_spring.backend.sql.model.MqTeachDTO;
import newb.c.a_spring.backend.sql.model.basemodel.MqStudent;
import newb.c.a_spring.backend.sql.model.basemodel.MqTeach;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Date;
import java.util.UUID;

public class SendRunable implements Runnable {

    private String exchange;

    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public SendRunable(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public SendRunable(String exchange, String routingKey, RabbitTemplate rabbitTemplate) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run() {
        MqTeachDTO mqTeachDTO;
        MqTeach mqTeach;
        MqStudent mqStudent;
        for (int i = 0; i < 500; i++) {
            mqTeachDTO =new MqTeachDTO();

            mqTeach =new MqTeach();
            String teachId = UUID.randomUUID().toString()+i;
            mqTeach.setId(UUID.randomUUID().toString());
            mqTeach.setInfo(exchange+"-"+routingKey+"-"+i+"：在"+System.currentTimeMillis()+"时间生成了ID为+"+teachId+"的对象");
            mqTeach.setName(teachId);
            mqTeach.setTime(new Date());

            mqStudent = new MqStudent();
            String studentId = UUID.randomUUID().toString()+i;
            mqStudent.setId(studentId);
            mqStudent.setInfo(exchange+"-"+routingKey+"-"+i+"：在"+System.currentTimeMillis()+"时间生成了ID为+"+studentId+"的对象");
            mqStudent.setName(studentId);
            mqStudent.setTeachId(teachId);
            mqStudent.setTime(new Date());

            mqTeachDTO.setMqTeach(mqTeach);
            mqTeachDTO.setMqStudent(mqStudent);

            rabbitTemplate.convertAndSend(exchange,routingKey,mqTeachDTO);
        }
    }
}
