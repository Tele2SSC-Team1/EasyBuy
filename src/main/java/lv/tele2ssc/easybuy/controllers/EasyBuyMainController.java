/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ivanssud
 */
@Controller
public class EasyBuyMainController {
    private static final Logger logger 
            = LoggerFactory.getLogger(EasyBuyMainController.class);
    
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String page(Model model) {
        
       // model.addAttribute("attribute", "value");
        return "index";
    }
       
}
