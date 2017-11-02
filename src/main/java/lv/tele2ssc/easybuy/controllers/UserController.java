package lv.tele2ssc.easybuy.controllers;

import lv.tele2ssc.easybuy.services.UserService;
import java.util.Objects;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;  
    
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
    
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult, Model model) {

        // some additional validation
        validateEmail(user, bindingResult);
        validatePassword(user, bindingResult);
        
        // validation isn't passed return back to registration form
        if (bindingResult.hasErrors()) {
            return "register";
        }

        boolean creating = user.getId() == null;
        userService.save(user);
        
        if (creating) {
            model.addAttribute("successMessage", "You are successfuly registered");
        } else {
            model.addAttribute("successMessage", "Profile is updated");
        }
        
        return "register";
    }

    private void validateEmail(User user, BindingResult bindingResult) {
        if (user == null) {
            return;
        }
        String email = user.getEmail();
        if (email == null) {
            return;
        }
        User existing = userService.findByEmail(email);
        if (existing != null && !Objects.equals(existing.getId(), user.getId())) {
            bindingResult.rejectValue("email", "already-exists", "User with this email already exists");
        }
    }

    private void validatePassword(User user, BindingResult bindingResult) {
        if (user == null) {
            return;
        }
        String p1 = user.getPassword();
        String p2 = user.getPassword2();
        if (p1 != null && !p1.equals(p2)) {
            bindingResult.rejectValue("password2", "pwd-not-match",  "Passwords don't match");
        }
    }
    
}
