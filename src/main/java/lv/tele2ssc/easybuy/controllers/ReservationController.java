package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.services.GoodsService;
import lv.tele2ssc.easybuy.services.ReservationService;
import lv.tele2ssc.easybuy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    
    @Autowired
    private UserService userService;  
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private ReservationService reservationService; 
    
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public String order(@RequestParam long goodsId, @RequestParam int orderAmount, Model model) {
        Goods goods = goodsService.findGoodById(goodsId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        
        reservationService.doReservation(user, goods, orderAmount);
        
        return "redirect:/mycart";   
    }
    
    @RequestMapping(path = "/mycart", method = RequestMethod.GET)
    public String mycart(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);
        Reservation reservation = currentUser.getCurrentReservation();
        model.addAttribute("reservation", reservation);
        
        return "mycart";        
    }
    
}
