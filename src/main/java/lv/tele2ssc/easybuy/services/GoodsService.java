package lv.tele2ssc.easybuy.services;

import java.util.List;
import lv.tele2ssc.easybuy.model.Category;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.repositories.CategoryRepository;
import lv.tele2ssc.easybuy.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    
    @Autowired
    private GoodsRepository goodsRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    
    public List<Category> findByCategory(String categoryName) {
        return categoryRepository.findByCategory(categoryName);
    }
    
    public void saveCategory(Category categories) {
        categoryRepository.save(categories);
    }   
    
    public List<Goods> findAllGoods() {
        return goodsRepository.findAll();
    }
    
    public Goods findById(long goodsId) {
        return goodsRepository.findOne(goodsId);
    }
    
    public List<Goods> findByTerm(String term) {
        return goodsRepository.findByTerm(term.toLowerCase());
    }
    
    public void saveGoods(Goods goods) {
        
        goodsRepository.save(goods);
    }
}
