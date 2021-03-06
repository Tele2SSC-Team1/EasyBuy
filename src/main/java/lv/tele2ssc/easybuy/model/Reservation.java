package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javax.persistence.CascadeType;

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
    
    @Column
    private float totalPrice;
    
    @Column
    private Boolean rated = false;

    @OneToMany(mappedBy="reservation", cascade = CascadeType.ALL)
    private List<ReservationGoods> reservationGoods;

    public Boolean getRated() {
        return rated;
    }

    public void setRated(Boolean rated) {
        this.rated = rated;
    }
    
   
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getCreated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
        return dateFormat.format( this.created );
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

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", client=" + client + ", status=" + status + ", created=" + created + ", totalPrice=" + totalPrice + ", rated=" + rated + ", reservationGoods=" + reservationGoods + '}';
    }

        
}
