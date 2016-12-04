package newb.c.model;

import java.util.ArrayList;  
import java.util.List;  
  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.JoinTable;  
import javax.persistence.ManyToMany;  
import javax.persistence.OneToMany;  
import javax.persistence.Table;  
import javax.persistence.Transient;  
  
@Entity  
@Table(name="t_role")  
public class TRole {  
  
    private Integer id;  
    private String rolename;  
    private List<TPermission> permissionList;//一个角色对应多个权限  
    private List<User> userList;//一个角色对应多个用户  
      
    @Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public Integer getId() {  
        return id;  
    }  
    public void setId(Integer id) {  
        this.id = id;  
    }  
    public String getRolename() {  
        return rolename;  
    }  
    public void setRolename(String rolename) {  
        this.rolename = rolename;  
    }  
    @OneToMany(mappedBy="role")  
    public List<TPermission> getPermissionList() {  
        return permissionList;  
    }  
    public void setPermissionList(List<TPermission> permissionList) {  
        this.permissionList = permissionList;  
    }  
    @ManyToMany  
    @JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})  
    public List<User> getUserList() {  
        return userList;  
    }  
    public void setUserList(List<User> userList) {  
        this.userList = userList;  
    }  
      
    @Transient  
    public List<String> getPermissionsName(){  
        List<String> list=new ArrayList<String>();  
        List<TPermission> perlist=getPermissionList();  
        for (TPermission per : perlist) {  
            list.add(per.getPermissionname());  
        }  
        return list;  
    }  
}
