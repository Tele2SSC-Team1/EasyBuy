package lv.tele2ssc.easybuy.controllers;

import java.io.IOException;
import java.util.List;
import lv.tele2ssc.easybuy.services.UserService;
import javax.validation.Valid;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.services.GoodsService;
import lv.tele2ssc.easybuy.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GoodsController {

    private static final Logger logger
            = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/new_item", method = RequestMethod.GET)
    public String newItem(Model model) {
        Goods goods = new Goods();
        List<Category> subCategories = goodsService.findAllSubCategories();
        model.addAttribute("goods", goods);
        model.addAttribute("subCategory", subCategories);

        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);
        return "new_item";
    }

    @RequestMapping(path = "/edit_item", method = RequestMethod.GET)
    public String editItem(@RequestParam long goodsId, Model model) {
        Goods goods = goodsService.findGoodById(goodsId);
        List<Category> subCategories = goodsService.findAllSubCategories();

        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();

        Category goodSubCategory = goods.getCategory();
        Category goodCategory = goodSubCategory.getParent();
        model.addAttribute("goods", goods);
        model.addAttribute("subCategory", subCategories);
        model.addAttribute("goodSubCategory", goodSubCategory);
        model.addAttribute("goodCategory", goodCategory);
        model.addAttribute("categories", categories);
        return "new_item";
    }

    @RequestMapping(path = "/new_item", method = RequestMethod.POST)
    public String newItem(@RequestParam(defaultValue = "0", required = false) Long categoryId, @Valid Goods goods, BindingResult bindingResult, @RequestParam MultipartFile image, Model model) {
        validateCategory(categoryId, bindingResult);
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
        storeImage(goods, image, bindingResult);

        if (bindingResult.hasErrors()) {
            return null;
        }

        Category cat = goodsService.findCategoryById(categoryId);
        goods.setCategory(cat);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(auth.getName());
        if (goods.getId() != null) {
            User seller = goodsService.findGoodById(goods.getId()).getSeller();
            if (seller != null) {
                goods.setSeller(seller);
            }
        } else {
            goods.setSeller(currentUser);
        }

        goodsService.saveGoods(goods);

        Category goodSubCategory = goods.getCategory();
        model.addAttribute("goodSubCategory", goodSubCategory);

        return "redirect:/good?goodsId=" + goods.getId();
    }

    private void storeImage(Goods goods, MultipartFile image, BindingResult bindingResult) {
        if (image.isEmpty()) {
            logger.debug("No image uploded preserving previous");
            Goods unchanged = goodsService.findGoodById(goods.getId());
            goods.setImageFileName(unchanged.getImageFileName());
        } else {
            logger.debug("Storing uploaded image {}", image.getOriginalFilename());
            goods.setImageFileName(image.getOriginalFilename());
            try {
                imageService.store(goods, image);
            } catch (IOException e) {
                logger.warn("Cannot save image", e);
                bindingResult.reject("image");
            }
        }
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

        //Category list for sidepanel
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

    @RequestMapping(path = "/good-image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getGoodImage(@RequestParam long goodId) {
        Resource file = imageService.loadImageAsResource(goodId);
        if (file == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
    }

}
