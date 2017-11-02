package lv.tele2ssc.easybuy.repositories;

import lv.tele2ssc.easybuy.model.Goods;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends CrudRepository<Goods, Long> {
    
    List<Goods> findAll();
    
    @Query("SELECT g FROM Goods g WHERE lower(g.name) like %?1% OR lower(g.description) like %?1% OR lower(g.code) like %?1%")
    java.util.List<Goods> findByTerm(String term);
}
