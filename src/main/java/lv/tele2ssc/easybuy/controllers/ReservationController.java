package lv.tele2ssc.easybuy.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    @RequestMapping(path = "/submit_reservation", method = RequestMethod.POST)
    public String submit_reservation(@ModelAttribute Reservation reservation, Model model) {
        for (ReservationGoods rg : reservation.getReservationGoods()) {
            ReservationGoods rgg = reservationService.findReservationGoodById(rg.getId());
            rgg.setAmount(rg.getAmount());
            reservationService.saveReservationGoods(rgg);
        }
        Reservation reserv = reservationService.findReservationById(reservation.getId());
        reservationService.changeTotalPrice(reserv);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);

        model.addAttribute("user", currentUser);

        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);

        return "submit_reservation";
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
    public String progressReservation(@Valid User user, BindingResult bindingResult, Model model) {
        validateAddress(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "submit_reservation";
        }

        model.addAttribute("successMessage", "You are successfully submit order. Our consultant will contact you soon");
        Reservation reservation = user.getCurrentReservation();
        reservation.setStatus(ReservationStatus.PROGRESS);
        for (ReservationGoods rg : reservation.getReservationGoods()) {
            rg.setStatus(ReservationStatus.PROGRESS);
            reservationService.saveReservationGoods(rg);
            rg.getGoods().setAmount(rg.getGoods().getAmount() - rg.getAmount());
            goodsService.saveGoods(rg.getGoods());
        }
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

        List<ReservationGoods> reservationGoods
                = reservationService.FindNotClosedReservationGoods(currentUser);

        reservationGoods.sort(
                Comparator.<ReservationGoods>comparingLong(g -> g.getReservation().getId())
                        .thenComparingLong(ReservationGoods::getId));

        model.addAttribute("reservationGoods", reservationGoods);

//Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();
        model.addAttribute("categories", categories);

        return "manage_reservation";

    }

    @RequestMapping(path = "/my_reservations", method = RequestMethod.GET)
    public String my_reservations(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByEmail(email);

        List<Reservation> reservations = reservationService.findAllByUser(currentUser);

        model.addAttribute("reservations", reservations);

        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();
        model.addAttribute("categories", categories);

        return "my_reservations";

    }

    @RequestMapping(path = "/closeReservation", method = RequestMethod.POST)
    public String closeReservation(@RequestParam(required = false) String reservationStatus, @RequestParam long reservationId, @RequestParam String action, Model model) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        if (action.equals("change_status")) {
            ReservationStatus status;
            switch (reservationStatus) {
                case "APPROVED":
                    status = ReservationStatus.APPROVED;
                    break;
                case "NOTRECIEVED":
                    status = ReservationStatus.NOTRECIEVED;
                    break;
                default:
                    status = ReservationStatus.CLOSED;
                    break;
            }

            reservation.setStatus(status);
            reservationService.saveReservation(reservation);
            for (ReservationGoods rg : reservation.getReservationGoods()) {
                rg.setStatus(status);
                reservationService.saveReservationGoods(rg);
            }

            return "redirect:/my_reservations";
        } else {
            return "redirect:/rate_reservation?reservationId=" + reservationId;
        }
    }

    @RequestMapping(path = "/rate_reservation", method = RequestMethod.GET)
    public String rate_reservation(@RequestParam long reservationId, Model model) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        model.addAttribute("reservation", reservation);
        List<Category> categories = goodsService.findAllCategories();
        model.addAttribute("categories", categories);
        return "rate_reservation";
    }

    @RequestMapping(path = "/rate_reservation", method = RequestMethod.POST)
    public String rate_reservation_post(@ModelAttribute Reservation reservation, Model model) {
        logger.debug("change reservation {}", reservation);
        Boolean status = Boolean.TRUE;
        for (ReservationGoods rg : reservation.getReservationGoods()) {
            logger.debug("change reserGood {}", rg);
            ReservationGoods rgg = reservationService.findReservationGoodById(rg.getId());
            Goods g = rgg.getGoods();
            logger.debug("change good {}", g.getRating());
            logger.debug("change good to {}", rg.getGoods().getRating());
            if (g.getRating() == 0L) {
                g.setRating(rg.getGoods().getRating());
                g.setVotes(1);
            } else {
                g.setRating((g.getRating() * g.getVotes() + rg.getGoods().getRating()) / (g.getVotes() + 1));
                g.setVotes(g.getVotes() + 1);
            }
            rgg.setRated(Boolean.TRUE);
            goodsService.saveGoods(g);
            reservationService.saveReservationGoods(rgg);

            if (Objects.equals(rgg.getRated(), Boolean.FALSE)) {
                status = Boolean.FALSE;
            }
        }
        Reservation reserv = reservationService.findReservationById(reservation.getId());
        reserv.setRated(status);
        reservationService.saveReservation(reserv);
        return "redirect:/rate_reservation?reservationId=" + reserv.getId();
    }

    @RequestMapping(path = "/approveReservation", method = RequestMethod.POST)
    public String approveReservation(@RequestParam String reservationGoodStatus, @RequestParam long reservationGoodId, Model model) {
        ReservationGoods reservationGoods = reservationService.findReservationGoodById(reservationGoodId);
        ReservationStatus status;
        switch (reservationGoodStatus) {
            case "PROGRESS":
                status = ReservationStatus.PROGRESS;
                break;
            case "DECLINED":
                status = ReservationStatus.DECLINED;
                break;
            default:
                status = ReservationStatus.APPROVED;
                break;
        }

        reservationGoods.setStatus(status);
        reservationService.saveReservationGoods(reservationGoods);
        Reservation reservation = reservationGoods.getReservation();
        if (status == ReservationStatus.DECLINED) {
            for (ReservationGoods rg : reservation.getReservationGoods()) {
                rg.setStatus(status);
                reservationService.saveReservationGoods(rg);
            }
        } else {
            for (ReservationGoods rg : reservation.getReservationGoods()) {
                if (rg.getStatus() != status) {
                    return "redirect:/manage_reservation";
                }
            }
        }
        reservation.setStatus(status);
        reservationService.saveReservation(reservation);
        return "redirect:/manage_reservation";
    }
}
