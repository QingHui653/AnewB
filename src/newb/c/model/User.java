package newb.c.model;

import javax.persistence.*;

public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;

    private String username;

    private String password;

    /**
     * 获取ID
     *
     * @return oid - ID
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * 设置ID
     *
     * @param oid ID
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}