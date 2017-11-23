package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.ReservationGoods;
import lv.tele2ssc.easybuy.model.ReservationStatus;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.services.GoodsService;
import lv.tele2ssc.easybuy.services.ReservationService;
import lv.tele2ssc.easybuy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private final static Logger logger = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public String order(@RequestParam long goodsId, @RequestParam(defaultValue = "1", required = false) int orderAmount, Model model) {

        Goods goods = goodsService.findGoodById(goodsId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        reservationService.doReservation(user, goods, orderAmount);
        model.addAttribute("successMessage", "You are successfully order item. Check cart");

        return "redirect:/good?goodsId=" + goodsId;
    }

    @RequestMapping(path = "/deleteFromReservation", method = RequestMethod.POST)
    public String deleteFromReservation(@RequestParam long reservationGoodsId, @RequestParam long reservationId, Model model) {
        ReservationGoods reservationGoods = reservationService.findReservationGoodById(reservationGoodsId);
        Reservation reservation = reservationService.findReservationById(reservationId);
        reservation.getReservationGoods().remove(reservationGoods);
        reservationService.saveReservation(reservation);

        return "redirect:/mycart";
    }

    @RequestMapping(path = "/changeAmount", method = RequestMethod.POST)
    public String changeAmount(@ModelAttribute Reservation reservation, Model model) {
        logger.debug("change amount {}", reservation);
        for (ReservationGoods rg : reservation.getReservationGoods()) {
            ReservationGoods rgg = reservationService.findReservationGoodById(rg.getId());
            rgg.setAmount(rg.getAmount());
            reservationService.saveReservationGoods(rgg);
        }

        return "redirect:/mycart";
    }

    @RequestMapping(path = "/mycart", method = RequestMethod.GET)
    public String mycart(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);
        float totalPrice = 0;
        Reservation reservation = currentUser.getCurrentReservation();
        if (reservation != null) {
            for (ReservationGoods rg : reservation.getReservationGoods()) {

                totalPrice = totalPrice + rg.getGoods().getPrice() * rg.getAmount();
            }
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("reservation", reservation);

        List<Category> categories = goodsService.findAllCategories();
        model.addAttribute("categories", categories);

        return "mycart";
    }

    @RequestMapping(path = "/submit_reservation", method = RequestMethod.GET)
    public String submit_reservation(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);

        model.addAttribute("user", currentUser);

        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);

        return "submit_reservation";
    }

    @RequestMapping(path = "/progress_reservation", method = RequestMethod.POST)
    public String close_reservation(@Valid User user, BindingResult bindingResult, Model model) {
        validateAddress(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "submit_reservation";
        }

        model.addAttribute("successMessage", "You are successfully submit order. Our consultant will contact you soon");

        Reservation reservation = user.getCurrentReservation();
        reservation.setStatus(ReservationStatus.PROGRESS);
        user.setCurrentReservation(null);
        userService.save(user);
        reservationService.saveReservation(reservation);

        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);

        return "submit_reservation";
    }

    private void validateAddress(User user, BindingResult bindingResult) {
        if (user == null) {
            return;
        }
        String address = user.getAddress();
        if (address == null || address == "") {
            bindingResult.rejectValue("address", "empty_address", "Please specify address");
        }
    }

    @RequestMapping(path = "/manage_reservation", method = RequestMethod.GET)
    public String manage_reservation(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);

        Reservation reservation = currentUser.getCurrentReservation();

        model.addAttribute("reservation", reservation);

//Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();
        model.addAttribute("categories", categories);

        return "manage_reservation";

    }
}
