package newb.c.a_spring.backend.sql.model.basemodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tid;

    private Integer index;

    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @return index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Title(String tid, Integer index, String content) {
        this.tid = tid;
        this.index = index;
        this.content = content;
    }
}