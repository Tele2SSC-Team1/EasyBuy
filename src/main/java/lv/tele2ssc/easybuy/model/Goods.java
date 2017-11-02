package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Goods implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    @NotEmpty
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    @Column
    @NotEmpty
    private String code;
    
    @JoinColumn (name="category_id")
    @NotEmpty
    @ManyToOne
    private Categories categoryId;
    
    @JoinColumn (name = "seller_id") 
    @ManyToOne
    private User sellerId;
    
    @Column
    @NotEmpty
    private int price;
    
    @Column
    @NotEmpty
    private String status;
    
    @Column
    @NotEmpty
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }
    
    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User sellerId) {
        this.sellerId = sellerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Column
    private String manufacturer;
}
