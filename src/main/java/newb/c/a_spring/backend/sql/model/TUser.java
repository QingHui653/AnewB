package newb.c.a_spring.backend.sql.model;

import java.util.HashSet;  
import java.util.List;  
import java.util.Set;  
  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.JoinTable;  
import javax.persistence.ManyToMany;  
import javax.persistence.Table;  
import javax.persistence.Transient;  
  
  
@Entity  
@Table(name="t_user")  
public class TUser {  
  
    private Integer id;  
    private String username;  
    private String password;  
    private List<TRole> roleList;//一个用户具有多个角色  
      
    @Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public Integer getId() {  
        return id;  
    }  
    public void setId(Integer id) {  
        this.id = id;  
    }  
    public String getUsername() {  
        return username;  
    }  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    @ManyToMany  
    @JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})  
    public List<TRole> getRoleList() {  
        return roleList;  
    }  
    public void setRoleList(List<TRole> roleList) {  
        this.roleList = roleList;  
    }  
      
    @Transient  
    public Set<String> getRolesName(){  
        List<TRole> roles=getRoleList();  
        Set<String> set=new HashSet<String>();  
        for (TRole role : roles) {  
            set.add(role.getRolename());  
        }  
        return set;  
    }  
      
}
