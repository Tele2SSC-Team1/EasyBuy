package lv.tele2ssc.easybuy.repositories;

import java.util.List;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
        
    List<Reservation> findAllByClient(User client);    
    
    @Query("SELECT r FROM Reservation r WHERE "
            + " r.status = 'CLOSED' AND r.client = ?2")
    List<Reservation> findClosedRervations(User user);
    
}