/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.repositories;

import java.util.List;
import lv.tele2ssc.easybuy.model.Categories;
import lv.tele2ssc.easybuy.model.Goods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ivanssud
 */
public interface CategoriesRepository extends CrudRepository<Categories, Long> {
    
    List<Categories> findAll();
    
    @Query("SELECT g FROM Categories g WHERE lower(g.categoryName) like %?1%")
    List<Categories> findByCategory(String categoryName);    
  
    
}
