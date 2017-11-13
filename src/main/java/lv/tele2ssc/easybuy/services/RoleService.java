/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lv.tele2ssc.easybuy.model.Role;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.repositories.RoleRepository;
import lv.tele2ssc.easybuy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstimc
 */
@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Set<Role> findRole(User user) {
        return user.getRoles();
    }

    public void saveRole(Set<Role> roles, User user) {
        user.setRoles(roles);
    }

}
