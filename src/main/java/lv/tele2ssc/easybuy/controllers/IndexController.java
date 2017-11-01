package lv.tele2ssc.easybuy.controllers;

import java.util.List;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {
    
    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(method = RequestMethod.GET, path="/")
    public String page(Model model) {
        
        List<Goods> goods = goodsService.findAllGoods();
        
        model.addAttribute("goods", goods);
        return "index";
    }
    
}
