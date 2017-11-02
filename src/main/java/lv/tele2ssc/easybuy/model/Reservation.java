package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "client_id")
    @NotEmpty
    private Long cliendId;

    @Column
    private String status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliendId() {
        return cliendId;
    }

    public void setCliendId(Long cliendId) {
        this.cliendId = cliendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
