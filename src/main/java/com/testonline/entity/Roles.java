
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
public class Roles implements Serializable{
    @Id
    @Column(name = "ID")
    private String roleId;
    
    @Column(name = "NAMEROLE")
    private String nameRole;
    
//    reference to User
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    List<User> listUser;
}
