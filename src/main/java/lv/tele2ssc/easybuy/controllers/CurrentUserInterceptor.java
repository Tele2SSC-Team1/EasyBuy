package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import java.util.Set;
import lv.tele2ssc.easybuy.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lv.tele2ssc.easybuy.model.Role;
import lv.tele2ssc.easybuy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * We need this interceptor to select current logged-in user 
 * and put it to the model of every page.
 * This should be configured in AppConfig class.
 */

@Component
public class CurrentUserInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CurrentUserInterceptor.class);
    
    @Autowired
    private UserService userService;
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Retriving email of currently logged in user.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return;
        }
        String email = auth.getName();
        // if user isn't logged in yet - email is null.
        if (email != null) {
            User currentUser = userService.findByEmail(email);
            
            modelAndView.addObject("currentUser", currentUser);
        }
    }
    
}
