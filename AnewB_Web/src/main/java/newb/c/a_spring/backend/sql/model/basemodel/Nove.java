package newb.c.a_spring.backend.sql.model.basemodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "nove")
public class Nove {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Column(name = "noveType")
    private String novetype;

    @Column(name = "titleType")
    private String titletype;

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
     * @return noveType
     */
    public String getNovetype() {
        return novetype;
    }

    /**
     * @param novetype
     */
    public void setNovetype(String novetype) {
        this.novetype = novetype;
    }

    /**
     * @return titleType
     */
    public String getTitletype() {
        return titletype;
    }

    /**
     * @param titletype
     */
    public void setTitletype(String titletype) {
        this.titletype = titletype;
    }
}