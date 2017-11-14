/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.controllers;

import lv.tele2ssc.easybuy.model.RoleAssigment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.Role;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.services.GoodsService;
import lv.tele2ssc.easybuy.services.RoleService;
import lv.tele2ssc.easybuy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author konstimc
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/edit_role", method = RequestMethod.GET)
    public String edit_role(Model model) {

        List<User> users = userService.findAllUsers();
//        List<Role> roles = roleService.findAllRoles();      
//        model.addAttribute("roles", roles);
        model.addAttribute("users", users);

        return "edit_role";
    }

    @RequestMapping(path = "/edit_one_role", method = RequestMethod.GET)
    public String edit_one_role(@RequestParam long userId, Model model) {

        User user = userService.findUser(userId);
        List<Role> roles = roleService.findAllRoles();
        
        List<RoleAssigment> rolesAssigments = new ArrayList();
        for (Role r:roles) {
            RoleAssigment ra= new RoleAssigment ();
            ra.setRole(r);
            ra.setChecked(user.getRoles().contains(r));
            rolesAssigments.add(ra);
        }
        user.setRoleAssigments(rolesAssigments);
        
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);

        return "edit_one_role";
    }

    @RequestMapping(path = "/edit_one_role", method = RequestMethod.POST)
    public String edit_one_role(@Valid User user, BindingResult bindingResult, Model model) {
        // checks whether edited book has validation errors
        if (bindingResult.hasErrors()) {
            return "edit_one_role"; 
        }
     
        //roleService.saveRole(roles, user);
        
        model.addAttribute("user", user);
        
        Set<Role> roles = new HashSet();
        for (RoleAssigment ra:user.getRoleAssigments()) {
            if (!ra.isChecked()) {
                continue;
            }
            long roleId = ra.getRoleId();
            Role role = roleService.getById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
         userService.saveUser(user);
        return "redirect:/edit_one_role?userId="+user.getId();
    }

    
}
