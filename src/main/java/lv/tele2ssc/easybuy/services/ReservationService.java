package lv.tele2ssc.easybuy.services;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import lv.tele2ssc.easybuy.model.Goods;
import lv.tele2ssc.easybuy.model.Reservation;
import lv.tele2ssc.easybuy.model.ReservationGoods;
import lv.tele2ssc.easybuy.model.ReservationStatus;
import lv.tele2ssc.easybuy.model.User;
import lv.tele2ssc.easybuy.repositories.ReservationGoodsRepository;
import lv.tele2ssc.easybuy.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
 
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ReservationGoodsRepository reservationGoodsRepository;
    
    public List<Reservation> findAllByUser(User client) {
        return reservationRepository.findAllByClient(client);
    }
    
    public void doReservation(User user, Goods goods, Integer orderAmount) {
        
        ReservationGoods reservationGoods = new ReservationGoods();
        reservationGoods.setGoods(goods);
        reservationGoods.setAmount(orderAmount);
        reservationGoodsRepository.save(reservationGoods);
        
        if (user.getCurrentReservation()==null) {
            Reservation reservation = new Reservation();
            reservation.setClient(user);
            reservation.setReservationGoods(Arrays.asList(reservationGoods));
            reservation.setCreated(new Timestamp(System.currentTimeMillis()));
            reservation.setStatus(ReservationStatus.NEW);
            reservationRepository.save(reservation);
        } else {
            Reservation reservation = user.getCurrentReservation();
            reservation.getReservationGoods().add(reservationGoods);
            reservationRepository.save(reservation);
        }
    }
   
}