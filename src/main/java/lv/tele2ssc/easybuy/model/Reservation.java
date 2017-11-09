package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private User client;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    
    @Column
    private Timestamp created;
    
    @OneToMany
    @JoinColumn(name = "reservation_id")
    private List<ReservationGoods> reservationGoods;
    
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public List<ReservationGoods> getReservationGoods() {
        return reservationGoods;
    }

    public void setReservationGoods(List<ReservationGoods> reservationGoods) {
        this.reservationGoods = reservationGoods;
    }

        
}
