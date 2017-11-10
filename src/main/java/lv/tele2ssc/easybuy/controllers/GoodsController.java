package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import lv.tele2ssc.easybuy.services.UserService;
import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
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
    public String newItem(@RequestParam long userId,@RequestParam long categoryId, @Valid Goods goods, BindingResult bindingResult, Model model) {
        
        // validation isn't passed return back to registration form
        if (bindingResult.hasErrors()) {
            return "register";
        }

        boolean creating = goods.getId() == null;
        
        User seller = userService.findUser(userId);
        goods.setSeller(seller);
        goods.setCategory(goodsService.findCategoryById(categoryId));
        goodsService.saveGoods(goods);
        
        
        if (creating) {
            model.addAttribute("successMessage", "You are successfuly add new item");
        } else {
            model.addAttribute("successMessage", "Item is updated");
        }
        
        return "new_item";
    }
    
    @RequestMapping(path = "/good", method = RequestMethod.GET)
    public String oneGood(@RequestParam long goodsId, Model model) {
        Goods g1 = goodsService.findGoodById(goodsId);
        Category c1 = g1.getCategory();
        User u1=g1.getSeller();
        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);
        
        model.addAttribute("goods", g1);
        model.addAttribute("category", c1);
        model.addAttribute("seller", u1);
        
        return "good";
    }
    
}
