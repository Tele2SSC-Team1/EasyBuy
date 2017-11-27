package lv.tele2ssc.easybuy.controllers.rest;

import java.util.List;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.services.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivanssud
 */
@RestController
public class GoodsRestController {

    private static final Logger logger
            = LoggerFactory.getLogger(GoodsRestController.class);

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(path="/api/subCategory",method=RequestMethod.GET)
    public List<Category> subCategory(@RequestParam Long id) {
        logger.debug("REST: searching for subCategory{}", id);
        
        Category parent =goodsService.findCategoryById(id);
        List<Category> list = goodsService.findSubCategories(parent);

       /* List<Category> subCategories = goodsService.findSubCategoriesById(id);*/
       
        
        
        return list;
    }
    
}
