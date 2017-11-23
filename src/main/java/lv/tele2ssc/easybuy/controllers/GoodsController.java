package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import lv.tele2ssc.easybuy.services.UserService;
import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.Role;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodsController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(path = "/new_item", method = RequestMethod.GET)
    public String newItem(Model model) {
        Goods goods = new Goods();
        List<Category> subCategories = goodsService.findAllSubCategories();
        model.addAttribute("goods", goods);
        model.addAttribute("subCategory", subCategories);
        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);
        return "new_item";
    }

    @RequestMapping(path = "/edit_item", method = RequestMethod.GET)
    public String editItem(@RequestParam long goodsId, Model model) {
        Goods goods = goodsService.findGoodById(goodsId);
        List<Category> subCategories = goodsService.findAllSubCategories();
        Category goodSubCategory = goods.getCategory();
        model.addAttribute("goods", goods);
        model.addAttribute("subCategory", subCategories);
        model.addAttribute("goodSubCategory", goodSubCategory);
        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);
        return "new_item";
    }

    @RequestMapping(path = "/new_item", method = RequestMethod.POST)
    public String newItem(@RequestParam long userId, @RequestParam(defaultValue = "0", required = false) long category, @Valid Goods goods, BindingResult bindingResult, Model model) {
        validateCategory(category, bindingResult);
        validateAmount(goods.getAmount(), bindingResult);
        validatePrice(goods.getPrice(), bindingResult);
        // validation isn't passed return back to registration form
        if (bindingResult.hasErrors()) {
            List<Category> subCategories = goodsService.findAllSubCategories();
            Category goodSubCategory = goods.getCategory();
            model.addAttribute("goodSubCategory", goodSubCategory);
            model.addAttribute("subCategory", subCategories);
            return "new_item";
        }
        User seller = userService.findUser(userId);
        goods.setSeller(seller);
        if (goods.getImgSrc() == null || goods.getImgSrc() == "") {
            goods.setImgSrc("http://via.placeholder.com/300x300");
        }
        goodsService.saveGoods(goods);

        Category goodSubCategory = goods.getCategory();
        model.addAttribute("goodSubCategory", goodSubCategory);

        return "redirect:/good?goodsId=" + goods.getId();
    }

    private void validateCategory(Long category, BindingResult bindingResult) {
        if (category == 0L) {
            bindingResult.rejectValue("category", "missed_category", "Please choose category");
        }
    }

    private void validateAmount(Integer amount, BindingResult bindingResult) {
        if (amount < 1) {
            bindingResult.rejectValue("amount", "incorrect_amount", "Please choose amount");
        }
    }

    private void validatePrice(Float price, BindingResult bindingResult) {
        if (price <= 0) {
            bindingResult.rejectValue("price", "incorrect_price", "Please choose price");
        }
    }

    @RequestMapping(path = "/good", method = RequestMethod.GET)
    public String oneGood(@RequestParam long goodsId, Model model) {
        Goods g1 = goodsService.findGoodById(goodsId);
        Category c1 = g1.getCategory();
        User u1 = g1.getSeller();
        List<Category> categories = goodsService.findAllCategories();

        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User currentUser = userService.findByEmail(email);
            Reservation reservation = currentUser.getCurrentReservation();
            model.addAttribute("reservation", reservation);
            model.addAttribute("categories", categories);
            model.addAttribute("goods", g1);
            model.addAttribute("category", c1);
            model.addAttribute("seller", u1);

            return "good";
        } catch (Exception e) {
            model.addAttribute("categories", categories);
            model.addAttribute("goods", g1);
            model.addAttribute("category", c1);
            model.addAttribute("seller", u1);

            return "good";
        }
    }

}
