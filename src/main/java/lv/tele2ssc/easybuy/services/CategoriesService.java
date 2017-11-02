/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.services;

import java.util.List;
import lv.tele2ssc.easybuy.model.Categories;
import lv.tele2ssc.easybuy.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivanssud
 */
@Service
public class CategoriesService {
  @Autowired
    private CategoriesRepository categoriesRepository;
    
    public List<Categories> findAllCategories() {
        return categoriesRepository.findAll();
    }
    
    public List<Categories> findByCategory(String categoryName) {
        return categoriesRepository.findByCategory(categoryName);
    }
    
    public void saveGoods(Categories categories) {
        categoriesRepository.save(categories);
    }   
}
