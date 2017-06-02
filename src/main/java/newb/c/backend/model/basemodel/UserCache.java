package newb.c.backend.model.basemodel;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "user_cache")
public class UserCache implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ID
     */
    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
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
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}