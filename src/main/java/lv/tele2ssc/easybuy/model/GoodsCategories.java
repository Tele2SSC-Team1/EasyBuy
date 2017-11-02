package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "goods_categories")
public class GoodsCategories implements Serializable {
    
    @EmbeddedId
    private GoodsCategoriesPK id;
    
    @ManyToOne
    @MapsId("goodsId") //This is the name of attr in GoodsCategoriesPK class
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;   
    
    @Column
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GoodsCategoriesPK getId() {
        return id;
    }

    public void setId(GoodsCategoriesPK id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
