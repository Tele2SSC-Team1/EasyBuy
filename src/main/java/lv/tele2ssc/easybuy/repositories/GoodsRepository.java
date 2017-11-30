package lv.tele2ssc.easybuy.repositories;

import lv.tele2ssc.easybuy.model.Goods;
import java.util.List;
import lv.tele2ssc.easybuy.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends CrudRepository<Goods, Long> {
    
    @Query("SELECT g FROM Goods g where g.amount>0 order by g.category.parent.categoryName, g.category.categoryName, g.name")
    public List<Goods> findAll();
    
    @Query("SELECT g FROM Goods g WHERE g.amount>0 and (lower(g.name) like %?1% OR lower(g.description) like %?1% OR lower(g.code) like %?1%)")
    List<Goods> findByTerm(String term);

    @Query("SELECT g FROM Goods g WHERE g.category = ?1 and g.amount>0 order by g.name")
    public List<Goods> findByCategory(Category category);
    
    @Query("SELECT max(g.id) FROM Goods g")
    public long findMaxId();
}
