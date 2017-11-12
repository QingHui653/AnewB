package newb.c.a_spring.backend.sql.model;

import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.ManyToOne;  
import javax.persistence.Table;  
  
@Entity  
@Table(name="t_permission")  
public class TPermission {  
  
    private Integer id;  
    private String permissionname;  
    private TRole role;//一个权限对应一个角色  
      
    @Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public Integer getId() {  
        return id;  
    }  
    public void setId(Integer id) {  
        this.id = id;  
    }  
    public String getPermissionname() {  
        return permissionname;  
    }  
    public void setPermissionname(String permissionname) {  
        this.permissionname = permissionname;  
    }  
    @ManyToOne  
    @JoinColumn(name="role_id")  
    public TRole getRole() {  
        return role;  
    }  
    public void setRole(TRole role) {  
        this.role = role;  
    }  
      
}  