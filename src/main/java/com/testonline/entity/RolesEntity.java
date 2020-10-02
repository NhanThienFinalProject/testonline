
package com.testonline.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesEntity implements Serializable{
    @Id
    @Column(name = "ID")
    private String roleId;
    
    @Column(name = "NAMEROLE")
    private String nameRole;
    
//    reference to UserEntity
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    List<UserEntity> listUser;

    public RolesEntity() {
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public List<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserEntity> listUser) {
        this.listUser = listUser;
    }
    
    
    
}
