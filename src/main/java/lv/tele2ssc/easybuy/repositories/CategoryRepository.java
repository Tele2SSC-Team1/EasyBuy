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
    
    @Query(value = "SELECT g FROM Category g WHERE g.parent is null")
    List<Category> findCategories();
    
   // @Query("SELECT g FROM Category g WHERE g.id like %?1%")
   // List<Category> findByCategory(Long Id);    
  
    
}
