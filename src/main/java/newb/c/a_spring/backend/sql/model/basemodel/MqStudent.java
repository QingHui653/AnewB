package newb.c.a_spring.backend.sql.model.basemodel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mq_student")
public class MqStudent {
    @Id
    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String id;

    @Column(name = "teach_id")
    private String teachId;

    private String name;

    private String info;

    private Date time;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return teach_id
     */
    public String getTeachId() {
        return teachId;
    }

    /**
     * @param teachId
     */
    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}