package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.services.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String page(Model model) {

        List<Goods> goods = goodsService.findAllGoods();
        
        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("goods", goods);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public String search(@RequestParam String term, Model model) {

        List<Goods> list = goodsService.findByTerm(term);
        
        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();
                
        model.addAttribute("categories", categories);
        model.addAttribute("goods", list);
        model.addAttribute("term", term);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category")
    public String page(@RequestParam Long categoryId, Model model) {

        Category category = goodsService.findCategoryById(categoryId);
        logger.debug("find category {}", category.getCategoryName());
        logger.debug("find category {}", category.getParent());
        List<Goods> goods = goodsService.findGoodsByCategory(category);
        
        //Category list for sidepanel
        List<Category> categories = goodsService.findAllCategories();
        
        if (category.getParent() == null) {
            List<Category> subCategories = goodsService.findSubCategories(category);
            category.setSubCategories(subCategories);
            for (Category c : subCategories) {
                logger.debug("find subCategory {}", c.getCategoryName());
                List<Goods> subGoods = goodsService.findGoodsByCategory(c);
                for (Goods g : subGoods) {
                    goods.add(g);
                }
            }
        }

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("goods", goods);
        return "index";
    }

}
