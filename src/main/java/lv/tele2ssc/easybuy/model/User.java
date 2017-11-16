package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "User", 
        uniqueConstraints=
            @UniqueConstraint(columnNames={"email"}))
public class User implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @Column(name = "full_name")
    @NotEmpty
    private String fullName;
    
    @Column(name = "phone_num")
    private Integer phoneNum;
    
    @Column
    private String address;
   
    @Column(name = "email")
    @NotEmpty
    private String email;
    
    @Column(name = "password")
    @NotEmpty
    private String password;
    
    @Transient
    private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
   
    @OneToOne
    @JoinColumn(name = "current_reservation")
    private Reservation currentReservation;
    
    @Transient
    private List<RoleAssigment> roleAssigments;
    public Reservation getCurrentReservation() {
        return currentReservation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }

    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
        public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleAssigment> getRoleAssigments() {
        return roleAssigments;
    }

    public void setRoleAssigments(List<RoleAssigment> roleAssigments) {
        this.roleAssigments = roleAssigments;
    }
    
    public boolean isManager() {
        for (Role role : this.getRoles()) {
            if (role.getName().equals("admin") || role.getName().equals("seller")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAdmin() {
        for (Role role : this.getRoles()) {
            if (role.getName().equals("admin")) {
                return true;
            }
        }
        return false;
    }
    
}
