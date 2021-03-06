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
import lv.tele2ssc.easybuy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ReservationGoodsRepository reservationGoodsRepository;
    
    public List<Reservation> findAllByUser(User client) {
        return reservationRepository.findAllByClient(client);
    }
    
    public void doReservation(User user, Goods goods, Integer orderAmount) {
        
        //To ovewrite amount, if corrections done
        if (user.getCurrentReservation()!=null) {
            for (ReservationGoods rg : user.getCurrentReservation().getReservationGoods()) {
                if (rg.getGoods()==goods) {
                    rg.setAmount(orderAmount);
                    reservationGoodsRepository.save(rg);
                    return;
                }
            }     
        }
        
        ReservationGoods reservationGoods = new ReservationGoods();
        reservationGoods.setGoods(goods);
        reservationGoods.setAmount(orderAmount);
        reservationGoodsRepository.save(reservationGoods);

        //
        if (user.getCurrentReservation()==null) {
            Reservation reservation = new Reservation();
            reservation.setClient(user);
            reservation.setReservationGoods(Arrays.asList(reservationGoods));
            reservation.setCreated(new Timestamp(System.currentTimeMillis()));
            reservation.setStatus(ReservationStatus.NEW);
            reservationGoods.setReservation(reservation);
            reservationGoods.setStatus(ReservationStatus.NEW);
            reservation.setTotalPrice(reservationGoods.getGoods().getPrice()*reservationGoods.getAmount());
            reservationRepository.save(reservation);
            reservationGoodsRepository.save(reservationGoods);
            user.setCurrentReservation(reservation);
            userRepository.save(user);
        } else {
            Reservation reservation = user.getCurrentReservation();
            reservation.getReservationGoods().add(reservationGoods);
            reservation.setTotalPrice(reservation.getTotalPrice() + reservationGoods.getGoods().getPrice()*reservationGoods.getAmount());
            reservationGoods.setReservation(reservation);
            reservationRepository.save(reservation);
            reservationGoodsRepository.save(reservationGoods);
        }
    }
    
    public void changeTotalPrice (Reservation reservation) {
        float totalPrice = 0L;
        for(ReservationGoods rg : reservation.getReservationGoods()) {
            totalPrice=totalPrice + rg.getGoods().getPrice()*rg.getAmount();
        }
        reservation.setTotalPrice(totalPrice);
        reservationRepository.save(reservation);
    }
    
    public ReservationGoods findReservationGoodById(long ReservationGoodsId) {
        return reservationGoodsRepository.findOne(ReservationGoodsId);
    }
    
    public Reservation findReservationById(long ReservationId) {
        return reservationRepository.findOne(ReservationId);
    }
    
    public void saveReservation(Reservation reservation) {
        
        reservationRepository.save(reservation);
    }
    
    public void saveReservationGoods(ReservationGoods reservationGoods) {
        
        reservationGoodsRepository.save(reservationGoods);
    }
    
    public List<ReservationGoods> FindNotClosedReservationGoods(User seller) {
        return reservationGoodsRepository.findNotClosed(seller);
        
    }
    
    public List<Reservation> FindNotClosedReservations(User seller) {
        return reservationRepository.findNotClosedReservations(seller);
    }
   
}