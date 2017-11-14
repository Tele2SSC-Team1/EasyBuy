/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.model;

import lv.tele2ssc.easybuy.model.Role;
import lv.tele2ssc.easybuy.model.User;

/**
 *
 * @author konstimc
 */
public class RoleAssigment {
    
    long roleId;
    Role role;
    boolean checked;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        this.roleId= role.getId();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    
}
