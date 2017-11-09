/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import java.util.Objects;
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
//        List<Role> roles = roleService.findAllRoles();      
//        model.addAttribute("roles", roles);
 //       model.addAttribute("users", users);

        return "edit_one_role";
    }
}
