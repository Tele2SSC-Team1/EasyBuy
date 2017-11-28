package lv.tele2ssc.easybuy.repositories;

import java.util.List;
import lv.tele2ssc.easybuy.model.ReservationGoods;
import lv.tele2ssc.easybuy.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationGoodsRepository extends CrudRepository<ReservationGoods, Long> {
                
    @Query("SELECT r FROM ReservationGoods r WHERE "
            + " (r.status = 'PROGRESS' OR r.status='APPROVED' OR r.status='NOTRECIEVED') and r.goods.seller = ?1")
    List<ReservationGoods> findNotClosed(User seller);
}