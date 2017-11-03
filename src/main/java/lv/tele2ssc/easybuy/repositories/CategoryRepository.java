/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.tele2ssc.easybuy.repositories;

import java.util.List;
import lv.tele2ssc.easybuy.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    
    List<Category> findAll();
    
    @Query("SELECT g FROM Category g WHERE lower(g.categoryName) like %?1%")
    List<Category> findByCategory(String categoryName);    
  
    
}
